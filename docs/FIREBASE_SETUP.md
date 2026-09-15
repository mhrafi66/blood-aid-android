# Firebase Setup

1. Create a Firebase project.
2. Register an Android app with package `io.github.mhrafi66.iutbloodaid`.
3. Download `google-services.json`.
4. Place it at `app/google-services.json`.
5. Enable Email/Password authentication.
6. Enable Firebase Realtime Database.

The application expects top-level database paths:

```text
Users
Donors
```

Do not use an open Realtime Database for a real production donor system. Production use requires appropriate Firebase security rules and privacy controls.
