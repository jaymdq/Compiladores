  ; ===========================================
  ; xactsrv.inc copyright MASM32 1998 - 2005
  ; ===========================================

IFNDEF XACTSRV_INC
XACTSRV_INC equ <1>

XsCaptureParameters PROTO :DWORD,:DWORD
XsCheckSmbDescriptor PROTO :DWORD,:DWORD
XsConvertServerEnumBuffer PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
XsDupStrToWStr PROTO :DWORD
XsDupWStrToStr PROTO :DWORD
XsI_NetNameCanonicalize PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsI_NetNameCompare PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsI_NetNameValidate PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsI_NetPathCanonicalize PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsI_NetPathCompare PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsI_NetPathType PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetAccessAdd PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetAccessDel PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetAccessEnum PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetAccessGetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetAccessGetUserPerms PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetAccessSetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetAccountDeltas PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetAccountSync PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetBuildGetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetCharDevControl PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetCharDevEnum PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetCharDevGetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetCharDevQEnum PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetCharDevQGetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetCharDevQPurge PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetCharDevQPurgeSelf PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetCharDevQSetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetConnectionEnum PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetFileClose2 PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetFileEnum2 PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetFileGetInfo2 PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetGetDCName PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetGroupAdd PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetGroupAddUser PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetGroupDel PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetGroupDelUser PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetGroupEnum PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetGroupGetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetGroupGetUsers PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetGroupSetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetGroupSetUsers PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetLogonEnum PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetMessageBufferSend PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetMessageNameAdd PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetMessageNameDel PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetMessageNameEnum PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetMessageNameGetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintDestAdd PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintDestControl PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintDestDel PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintDestEnum PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintDestGetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintDestSetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintJobContinue PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintJobDel PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintJobEnum PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintJobGetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintJobPause PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintJobSetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintQAdd PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintQContinue PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintQDel PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintQEnum PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintQGetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintQPause PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintQPurge PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetPrintQSetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetRemoteTOD PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetServerAuthenticate PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetServerDiskEnum PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetServerEnum2 PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetServerEnum3 PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetServerGetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetServerPasswordSet PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetServerReqChallenge PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetServerSetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetServiceControl PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetServiceEnum PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetServiceGetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetServiceInstall PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetSessionDel PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetSessionEnum PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetSessionGetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetShareAdd PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetShareCheck PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetShareDel PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetShareEnum PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetShareGetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetShareSetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetStatisticsGet2 PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetUnsupportedApi PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetUseAdd PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetUseDel PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetUseEnum PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetUseGetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetUserAdd2 PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetUserDel PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetUserEnum2 PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetUserEnum PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetUserGetGroups PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetUserGetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetUserModalsGet PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetUserModalsSet PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetUserPasswordSet2 PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetUserSetGroups PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetUserSetInfo2 PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetUserSetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetWkstaGetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetWkstaSetInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetWkstaSetUID PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetWkstaUserLogoff PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsNetWkstaUserLogon PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsSamOEMChangePasswordUser2_P PROTO :DWORD,:DWORD,:DWORD,:DWORD
XsSetParameters PROTO :DWORD,:DWORD,:DWORD

ELSE
echo -----------------------------------------
echo WARNING Duplicate include file xactsrv.inc
echo -----------------------------------------
ENDIF
