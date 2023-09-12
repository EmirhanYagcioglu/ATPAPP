# ATPAPP
Autonomous Tank Project (ATP) PC side application used for communication with and to control the ATP.

## VERSION HISTORY & CHANGELOG

### 9/5/2023 ver 0
Initial commit of the application.

### 9/8/2023 ver 0.0.1
Primitive application with no UI.
- Auto target device finder
- PC Tx
- PC Rx
- Tx & Rx Integration
- Full speed enabled

As of now, it is possible to establish a two way connection between the app and ATP, without any delay.
- UART 
- 115200 baud
- 4-byte long data packets

### 9/12/2023 ver 0.1
Full command enabled version.
- Now full commands are sendable to KL25z
- Source code reshuffle & beautification
- Added command interpretor
