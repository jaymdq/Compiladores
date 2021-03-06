  ; ===========================================
  ; spoolss.inc copyright MASM32 1998 - 2005
  ; ===========================================

IFNDEF SPOOLSS_INC
SPOOLSS_INC equ <1>

AbortPrinter PROTO :DWORD
AddDriverCatalog PROTO :DWORD,:DWORD,:DWORD,:DWORD
AddFormW PROTO :DWORD,:DWORD,:DWORD
AddJobW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
AddMonitorW PROTO :DWORD,:DWORD,:DWORD
AddPerMachineConnectionW PROTO :DWORD,:DWORD,:DWORD,:DWORD
AddPortExW PROTO :DWORD,:DWORD,:DWORD,:DWORD
AddPortW PROTO :DWORD,:DWORD,:DWORD
AddPrintProcessorW PROTO :DWORD,:DWORD,:DWORD,:DWORD
AddPrintProvidorW PROTO :DWORD,:DWORD,:DWORD
AddPrinterConnectionW PROTO :DWORD
AddPrinterDriverExW PROTO :DWORD,:DWORD,:DWORD,:DWORD
AddPrinterDriverW PROTO :DWORD,:DWORD,:DWORD
AddPrinterExW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
AddPrinterW PROTO :DWORD,:DWORD,:DWORD
AdjustPointers PROTO :DWORD,:DWORD,:DWORD
AdjustPointersInStructuresArray PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
AlignKMPtr PROTO :DWORD,:DWORD
AlignRpcPtr PROTO :DWORD,:DWORD
AllocSplStr PROTO :DWORD
AppendPrinterNotifyInfoData PROTO :DWORD,:DWORD,:DWORD
BuildOtherNamesFromMachineName PROTO :DWORD,:DWORD
CallDrvDevModeConversion PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
CallRouterFindFirstPrinterChangeNotification PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
ClosePrinter PROTO :DWORD
ClusterSplClose PROTO :DWORD
ClusterSplIsAlive PROTO :DWORD
ClusterSplOpen PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
ConfigurePortW PROTO :DWORD,:DWORD,:DWORD
CreatePrinterIC PROTO :DWORD,:DWORD
DbgGetPointers PROTO 
DeleteFormW PROTO :DWORD,:DWORD
DeleteMonitorW PROTO :DWORD,:DWORD,:DWORD
DeletePerMachineConnectionW PROTO :DWORD,:DWORD
DeletePortW PROTO :DWORD,:DWORD,:DWORD
DeletePrintProcessorW PROTO :DWORD,:DWORD,:DWORD
DeletePrintProvidorW PROTO :DWORD,:DWORD,:DWORD
DeletePrinter PROTO :DWORD
DeletePrinterConnectionW PROTO :DWORD
DeletePrinterDataExW PROTO :DWORD,:DWORD,:DWORD
DeletePrinterDataW PROTO :DWORD,:DWORD
DeletePrinterDriverExW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
DeletePrinterDriverW PROTO :DWORD,:DWORD,:DWORD
DeletePrinterIC PROTO :DWORD
DeletePrinterKeyW PROTO :DWORD,:DWORD
DllAllocSplMem PROTO :DWORD
DllFreeSplMem PROTO :DWORD
DllFreeSplStr PROTO :DWORD
EndDocPrinter PROTO :DWORD
EndPagePrinter PROTO :DWORD
EnumFormsW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EnumJobsW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EnumMonitorsW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EnumPerMachineConnectionsW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EnumPortsW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EnumPrintProcessorDatatypesW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EnumPrintProcessorsW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EnumPrinterDataExW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EnumPrinterDataW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EnumPrinterDriversW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EnumPrinterKeyW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EnumPrintersW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
FindClosePrinterChangeNotification PROTO :DWORD
FlushPrinter PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
FormatPrinterForRegistryKey PROTO :DWORD,:DWORD,:DWORD
FormatRegistryKeyForPrinter PROTO :DWORD,:DWORD,:DWORD
FreeOtherNames PROTO :DWORD,:DWORD
GetClientUserHandle PROTO :DWORD
GetFormW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
GetJobAttributes PROTO :DWORD,:DWORD,:DWORD
GetJobW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
GetNetworkId PROTO :DWORD,:DWORD
GetPrintProcessorDirectoryW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
GetPrinterDataExW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
GetPrinterDataW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
GetPrinterDriverDirectoryW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
GetPrinterDriverExW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
GetPrinterDriverW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
GetPrinterW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
GetShrinkedSize PROTO :DWORD,:DWORD
ImpersonatePrinterClient PROTO :DWORD
InitializeRouter PROTO :DWORD
IsLocalCall PROTO 
IsNamedPipeRpcCall PROTO 
LoadDriver PROTO :DWORD
LoadDriverFiletoConvertDevmode PROTO :DWORD
LoadDriverWithVersion PROTO :DWORD,:DWORD
LogWmiTraceEvent PROTO :DWORD,:DWORD,:DWORD
MIDL_user_allocate1 PROTO :DWORD
MIDL_user_free1 PROTO :DWORD
MarshallDownStructure PROTO :DWORD,:DWORD,:DWORD,:DWORD
MarshallDownStructuresArray PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
MarshallUpStructure PROTO :DWORD,:DWORD,:DWORD,:DWORD
MarshallUpStructuresArray PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
OldGetPrinterDriverW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
OpenPrinterExW PROTO :DWORD,:DWORD,:DWORD,:DWORD
OpenPrinterPortW PROTO :DWORD,:DWORD,:DWORD
OpenPrinterW PROTO :DWORD,:DWORD,:DWORD
PackStrings PROTO :DWORD,:DWORD,:DWORD,:DWORD
PartialReplyPrinterChangeNotification PROTO :DWORD,:DWORD
PlayGdiScriptOnPrinterIC PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
PrinterHandleRundown PROTO :DWORD
PrinterMessageBoxW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
ProvidorFindClosePrinterChangeNotification PROTO :DWORD
ProvidorFindFirstPrinterChangeNotification PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
ReadPrinter PROTO :DWORD,:DWORD,:DWORD,:DWORD
ReallocSplMem PROTO :DWORD,:DWORD,:DWORD
ReallocSplStr PROTO :DWORD,:DWORD
RemoteFindFirstPrinterChangeNotification PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
ReplyClosePrinter PROTO :DWORD
ReplyOpenPrinter PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
ReplyPrinterChangeNotification PROTO :DWORD,:DWORD,:DWORD,:DWORD
ResetPrinterW PROTO :DWORD,:DWORD
RevertToPrinterSelf PROTO 
RouterAllocBidiMem PROTO :DWORD
RouterAllocBidiResponseContainer PROTO :DWORD
RouterAllocPrinterNotifyInfo PROTO :DWORD
RouterFindFirstPrinterChangeNotification PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
RouterFindNextPrinterChangeNotification PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
RouterFreeBidiMem PROTO :DWORD
RouterFreePrinterNotifyInfo PROTO :DWORD
RouterRefreshPrinterChangeNotification PROTO :DWORD,:DWORD,:DWORD,:DWORD
RouterReplyPrinter PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
ScheduleJob PROTO :DWORD,:DWORD
SeekPrinter PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
SendRecvBidiData PROTO :DWORD,:DWORD,:DWORD,:DWORD
SetAllocFailCount PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
SetFormW PROTO :DWORD,:DWORD,:DWORD,:DWORD
SetJobW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
SetPortW PROTO :DWORD,:DWORD,:DWORD,:DWORD
SetPrinterDataExW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
SetPrinterDataW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
SetPrinterW PROTO :DWORD,:DWORD,:DWORD,:DWORD
SplCloseSpoolFileHandle PROTO :DWORD
SplCommitSpoolData PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
SplDriverUnloadComplete PROTO :DWORD
SplGetSpoolFileInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
SplInitializeWinSpoolDrv PROTO :DWORD
SplIsSessionZero PROTO :DWORD,:DWORD,:DWORD
SplIsUpgrade PROTO 
SplPowerEvent PROTO :DWORD
SplProcessPnPEvent PROTO :DWORD,:DWORD,:DWORD
SplPromptUIInUsersSession PROTO :DWORD,:DWORD,:DWORD,:DWORD
SplReadPrinter PROTO :DWORD,:DWORD,:DWORD
SplRegisterForDeviceEvents PROTO :DWORD,:DWORD,:DWORD
SplShutDownRouter PROTO 
SplStartPhase2Init PROTO 
SplUnregisterForDeviceEvents PROTO :DWORD
SpoolerFindClosePrinterChangeNotification PROTO :DWORD
SpoolerFindFirstPrinterChangeNotification PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
SpoolerFindNextPrinterChangeNotification PROTO :DWORD,:DWORD,:DWORD,:DWORD
SpoolerFreePrinterNotifyInfo PROTO :DWORD
SpoolerHasInitialized PROTO 
SpoolerInit PROTO 
StartDocPrinterW PROTO :DWORD,:DWORD,:DWORD
StartPagePrinter PROTO :DWORD
UndoAlignKMPtr PROTO :DWORD,:DWORD
UndoAlignRpcPtr PROTO :DWORD,:DWORD,:DWORD,:DWORD
UnloadDriver PROTO :DWORD
UnloadDriverFile PROTO :DWORD
UpdateBufferSize PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
UpdatePrinterRegAll PROTO :DWORD,:DWORD,:DWORD
UpdatePrinterRegUser PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
WaitForPrinterChange PROTO :DWORD,:DWORD
WaitForSpoolerInitialization PROTO 
WritePrinter PROTO :DWORD,:DWORD,:DWORD,:DWORD
XcvDataW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
bGetDevModePerUser PROTO :DWORD,:DWORD,:DWORD
bSetDevModePerUser PROTO :DWORD,:DWORD,:DWORD
vDbgLogError PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD

ELSE
echo -----------------------------------------
echo WARNING Duplicate include file spoolss.inc
echo -----------------------------------------
ENDIF
