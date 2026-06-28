# Security Audit – Kavkom Phone v1.1.0

## Executive Summary

**Application**

* Package: `com.kavkom.phone`
* Version: **1.1.0**
* Version Code: **7**
* Release build
* React Native
* Hermes enabled
* React Native New Architecture enabled

---

# 1. Network Infrastructure

## Production REST API

```
https://webphoneapi.kavkom.com/api/v1
```

Purpose:

* Authentication
* User profile
* Call management
* Backend services

Source:

* BuildConfig
* XML resources

---

## Development API

```
https://apitestwebphone.kavkom.com/api/v1/
```

This endpoint remains embedded inside the production APK.

Potential concern:

* Development environments are frequently less protected than production.
* It may expose test accounts or debugging functionality if reachable.

---

# 2. SIP Infrastructure

## Proxy

```
wss://pn.kavkom.com:8070
```

### Protocol

```
WebSocket Secure (RFC7118)
        │
        ▼
TLS
        │
HTTP Upgrade
        │
        ▼
WebSocket
        │
        ▼
SIP Signalling
```

The application almost certainly performs SIP signaling using **RFC 7118 (SIP over WebSocket)**.

Expected SIP messages include:

```
REGISTER
INVITE
ACK
CANCEL
BYE
OPTIONS
MESSAGE
SUBSCRIBE
NOTIFY
```

Port:

```
8070
```

is non-standard and likely fronts a SIP proxy such as:

* Kamailio
* OpenSIPS
* Asterisk
* FreeSWITCH

---

## SIP Logging

```
REACT_APP_SIP_LOG_LEVEL = error
```

Meaning:

Verbose SIP traces are disabled in production.

---

## Audio Logging

```
REACT_APP_ENABLE_AUDIO_LOGS = true
```

This is unusual for a production build. While it does not prove audio is stored or transmitted, it indicates that audio-related diagnostics are enabled and warrants verification of how those logs are handled. 

---

# 3. Firebase Configuration

Unlike BuildConfig, the XML exposes the full Firebase configuration.

## Firebase Project

```
Project Number
369407881648
```

```
Project ID
kavkom-webphone
```

```
Storage Bucket
kavkom-webphone.appspot.com
```

```
Google App ID

1:369407881648:android:23297463cc53f9a70aa675
```



---

## Google API Key

```
AIzaSyC8VIbaeYUzRhA4Z-bS6EjIY3V9nnEC-8M
```

Found as:

```
google_api_key
```

and

```
google_crash_reporting_api_key
```



### Important

This is a **Google API key**, not a private secret. Android Firebase applications commonly embed such keys in the client. Their security depends on proper restrictions in the associated Google Cloud project (for example, limiting use to the application's package name and signing certificate). If those restrictions are absent or misconfigured, unauthorized use of enabled Google APIs may be possible.

Recommended verification:

* Android application restrictions.
* API restrictions.
* Enabled Google services.

---

# 4. OAuth Configuration

Embedded OAuth Client ID:

```
369407881648-hane4d7bplavmsho62u0s58nf6m218a4.apps.googleusercontent.com
```

Resource:

```
default_web_client_id
```



This client ID is expected for Firebase Authentication and Google Sign-In integrations. Like API keys, client IDs are public identifiers rather than secrets.

---

# 5. Sentry

Embedded DSN:

```
https://76f227daf98340e3aae6f61ccae04f49@o201483.ingest.sentry.io/6655428
```

Purpose:

* Crash reports
* Stack traces
* Device diagnostics

The DSN is public by design but could be abused to submit excessive events if server-side controls are not in place.

---

# 6. React Native Development Artifacts

The resources include:

```
react_native_dev_server_ip

192.168.28.29
```



This is a strong indication that the application was at some point built against a local Metro development server. In a release APK it is generally unused, but its presence suggests development artifacts were not fully stripped.

The resources also contain the full React Native developer menu strings:

```
Reload
Open DevTools
Fast Refresh
Capture Heap
Toggle Element Inspector
Performance Monitor
```



These strings alone do **not** indicate that developer features are available in production.

---

# 7. Build Configuration

The application is simultaneously configured as:

```
DEBUG = false
```

while also containing:

```
REACT_APP_IS_DEVELOPMENT_MODE = true
```

This mismatch is one of the more interesting findings because it suggests application behavior may be controlled by JavaScript configuration rather than the Android build type alone. Determining the practical impact requires analysis of the JavaScript bundle.

---

# 8. Exposed Configuration Summary

| Item                       | Status   | Security Impact              |
| -------------------------- | -------- | ---------------------------- |
| Production API URL         | Embedded | Low                          |
| Test API URL               | Embedded | Medium                       |
| SIP WSS Proxy              | Embedded | Medium                       |
| Google API Key             | Embedded | Low (if properly restricted) |
| Firebase Project ID        | Embedded | Informational                |
| Firebase Storage Bucket    | Embedded | Informational                |
| Google OAuth Client ID     | Embedded | Informational                |
| Sentry DSN                 | Embedded | Low                          |
| Apple Team ID              | Embedded | Informational                |
| React Native Dev Server IP | Embedded | Low                          |
| Audio Logging Enabled      | Enabled  | Review recommended           |
| Development Mode Flag      | Enabled  | Medium                       |

---

# Recommended Next Steps

To move from a configuration review to a deeper security assessment, the following components should be analyzed next:

1. **`AndroidManifest.xml`** to inspect permissions, exported activities/services/receivers, deep links, and network security configuration.
2. **`network_security_config.xml`** to determine whether certificate pinning, custom trust anchors, or cleartext traffic exceptions are configured.
3. **The React Native JavaScript bundle or Hermes bytecode** to inspect authentication logic, API usage, feature flags, and any client-side authorization decisions.
4. **`classes.dex`** to analyze certificate validation, SIP registration, token handling, and interactions with Firebase or backend services.
5. **Runtime network traffic** (using a test account and appropriate authorization) to verify TLS behavior, API authentication, SIP signaling, and whether additional protections such as certificate pinning or rate limiting are enforced.
