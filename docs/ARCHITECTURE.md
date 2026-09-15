# Architecture

IUT Blood Aid uses a traditional Android Activity-based architecture.

- `Splash`: startup/authentication routing
- `LoginActivity`: sign in and password reset
- `SignUpActivity`: account creation and email verification
- `ChooseActivity`: main navigation
- `DonorReg`: donor registration
- `SearchActivity`: blood-group selection
- `ResultActivity`: Firebase donor lookup and filtering
- `DonorAdapter`: donor result rendering and dialer launch
- `ProfileActivity`: last-donation date and eligibility updates
- `Donor`: Firebase donor data model
- `WaitApproval`: donor approval information

Firebase Authentication manages user identity. Firebase Realtime Database stores users and donors.
