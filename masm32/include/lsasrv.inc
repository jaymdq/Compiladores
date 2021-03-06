  ; ===========================================
  ; lsasrv.inc copyright MASM32 1998 - 2005
  ; ===========================================

IFNDEF LSASRV_INC
LSASRV_INC equ <1>

DsRolerDcAsDc PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
DsRolerDcAsReplica PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
DsRolerDemoteDc PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
DsRolerGetDcOperationProgress PROTO :DWORD,:DWORD,:DWORD
DsRolerGetDcOperationResults PROTO :DWORD,:DWORD,:DWORD
LsaIAllocateHeap PROTO :DWORD
LsaIAllocateHeapZero PROTO :DWORD
LsaIAuditAccountLogon PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaIAuditAccountLogonEx PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaIAuditKdcEvent PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaIAuditKerberosLogon PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaIAuditLogonUsingExplicitCreds PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaIAuditNotifyPackageLoad PROTO :DWORD
LsaIAuditPasswordAccessEvent PROTO :DWORD,:DWORD,:DWORD
LsaIAuditSamEvent PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaICallPackage PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaICallPackageEx PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaICallPackagePassthrough PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaICancelNotification PROTO :DWORD
LsaIChangeSecretCipherKey PROTO :DWORD
LsaICryptProtectData PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaICryptUnprotectData PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaIDsNotifiedObjectChange PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaIEnumerateSecrets PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaIEventNotify PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsaIFilterSids PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaIForestTrustFindMatch PROTO :DWORD,:DWORD,:DWORD
LsaIFreeForestTrustInfo PROTO :DWORD
LsaIFreeHeap PROTO :DWORD
LsaIFreeReturnBuffer PROTO :DWORD
LsaIFree_LSAI_PRIVATE_DATA PROTO :DWORD
LsaIFree_LSAI_PRIVATE_DAT equ <LsaIFree_LSAI_PRIVATE_DATA>

LsaIFree_LSAI_SECRET_ENUM_BUFFER PROTO :DWORD,:DWORD
LsaIFree_LSAPR_ACCOUNT_ENUM_BUFFER PROTO :DWORD
LsaIFree_LSAPR_CR_CIPHER_VALUE PROTO :DWORD
LsaIFree_LSAPR_POLICY_DOMAIN_INFORMATION PROTO :DWORD,:DWORD
LsaIFree_LSAPR_POLICY_INFORMATION PROTO :DWORD,:DWORD
LsaIFree_LSAPR_PRIVILEGE_ENUM_BUFFER PROTO :DWORD
LsaIFree_LSAPR_PRIVILEGE_SET PROTO :DWORD
LsaIFree_LSAPR_REFERENCED_DOMAIN_LIST PROTO :DWORD
LsaIFree_LSAPR_SR_SECURITY_DESCRIPTOR PROTO :DWORD
LsaIFree_LSAPR_TRANSLATED_NAMES PROTO :DWORD
LsaIFree_LSAPR_TRANSLATED_SIDS PROTO :DWORD
LsaIFree_LSAPR_TRUSTED_DOMAIN_INFO PROTO :DWORD,:DWORD
LsaIFree_LSAPR_TRUSTED_ENUM_BUFFER PROTO :DWORD
LsaIFree_LSAPR_TRUSTED_ENUM_BUFFER_EX PROTO :DWORD
LsaIFree_LSAPR_TRUST_INFORMATION PROTO :DWORD
LsaIFree_LSAPR_UNICODE_STRING PROTO :DWORD
LsaIFree_LSAPR_UNICODE_STRING_BUFFER PROTO :DWORD
LsaIFree_LSAP_SITENAME_INFO PROTO :DWORD
LsaIFree_LSAP_SITE_INFO PROTO :DWORD
LsaIFree_LSAP_SUBNET_INFO PROTO :DWORD
LsaIFree_LSAP_UPN_SUFFIXES PROTO :DWORD
LsaIFree_LSA_FOREST_TRUST_COLLISION_INFORMATION PROTO :DWORD
LsaIFree_LSA_FOREST_TRUST_INFORMATION PROTO :DWORD
LsaIGetBootOption PROTO :DWORD
LsaIGetCallInfo PROTO :DWORD
LsaIGetForestTrustInformation PROTO :DWORD
LsaIGetLogonGuid PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaIGetNbAndDnsDomainNames PROTO :DWORD,:DWORD,:DWORD
LsaIGetPrivateData PROTO :DWORD,:DWORD,:DWORD
LsaIGetSerialNumberPolicy2 PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsaIGetSerialNumberPolicy PROTO :DWORD,:DWORD,:DWORD
LsaIGetSiteName PROTO :DWORD
LsaIHealthCheck PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsaIImpersonateClient PROTO 
LsaIInitializeWellKnownSids PROTO :DWORD
LsaIIsClassIdLsaClass PROTO :DWORD,:DWORD
LsaIIsDsPaused PROTO 
LsaIKerberosRegisterTrustNotification PROTO :DWORD,:DWORD
LsaILookupWellKnownName PROTO :DWORD
LsaINotifyChangeNotification PROTO :DWORD
LsaINotifyNetlogonParametersChangeW PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsaINotifyPasswordChanged PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaIOpenPolicyTrusted PROTO :DWORD
LsaIQueryForestTrustInfo PROTO :DWORD,:DWORD
LsaIQueryInformationPolicyTrusted PROTO :DWORD,:DWORD
LsaIQuerySiteInfo PROTO :DWORD
LsaIQuerySubnetInfo PROTO :DWORD
LsaIQueryUpnSuffixes PROTO :DWORD
LsaIRegisterNotification PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaIRegisterPolicyChangeNotificationCallback PROTO :DWORD,:DWORD
LsaISafeMode PROTO 
LsaISamIndicatedDsStarted PROTO :DWORD
LsaISetBootOption PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaISetClientDnsHostName PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsaISetLogonGuidInLogonSession PROTO :DWORD,:DWORD
LsaISetPrivateData PROTO :DWORD,:DWORD,:DWORD
LsaISetSerialNumberPolicy PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsaISetTimesSecret PROTO :DWORD,:DWORD,:DWORD
LsaISetupWasRun PROTO 
LsaITestCall PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsaIUnregisterAllPolicyChangeNotificationCallback PROTO :DWORD
LsaIUnregisterPolicyChangeNotificationCallback PROTO :DWORD,:DWORD
LsaIUpdateForestTrustInformation PROTO :DWORD,:DWORD,:DWORD
LsaIWriteAuditEvent PROTO :DWORD,:DWORD
LsapAuOpenSam PROTO :DWORD
LsapCheckBootMode PROTO 
LsapDsDebugInitialize PROTO 
LsapDsInitializeDsStateInfo PROTO :DWORD
LsapDsInitializePromoteInterface PROTO 
LsapInitLsa PROTO 
LsarAddPrivilegesToAccount PROTO :DWORD,:DWORD
LsarClose PROTO :DWORD
LsarCreateAccount PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsarCreateSecret PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsarCreateTrustedDomain PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsarCreateTrustedDomainEx PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsarDelete PROTO :DWORD
LsarEnumerateAccounts PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsarEnumeratePrivileges PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsarEnumeratePrivilegesAccount PROTO :DWORD,:DWORD
LsarEnumerateTrustedDomains PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsarEnumerateTrustedDomainsEx PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsarGetQuotasForAccount PROTO :DWORD,:DWORD
LsarGetSystemAccessAccount PROTO :DWORD,:DWORD
LsarLookupNames PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsarLookupPrivilegeDisplayName PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsarLookupPrivilegeName PROTO :DWORD,:DWORD,:DWORD
LsarLookupPrivilegeValue PROTO :DWORD,:DWORD,:DWORD
LsarLookupSids2 PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsarLookupSids PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsarOpenAccount PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsarOpenPolicy PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsarOpenPolicySce PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsarOpenSecret PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsarOpenTrustedDomain PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsarOpenTrustedDomainByName PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsarQueryDomainInformationPolicy PROTO :DWORD,:DWORD,:DWORD
LsarQueryForestTrustInformation PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsarQueryInfoTrustedDomain PROTO :DWORD,:DWORD,:DWORD
LsarQueryInformationPolicy PROTO :DWORD,:DWORD,:DWORD
LsarQuerySecret PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsarQuerySecurityObject PROTO :DWORD,:DWORD,:DWORD
LsarQueryTrustedDomainInfo PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsarQueryTrustedDomainInfoByName PROTO :DWORD,:DWORD,:DWORD,:DWORD
LsarRemovePrivilegesFromAccount PROTO :DWORD,:DWORD,:DWORD
LsarSetDomainInformationPolicy PROTO :DWORD,:DWORD,:DWORD
LsarSetForestTrustInformation PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LsarSetInformationPolicy PROTO :DWORD,:DWORD,:DWORD
LsarSetInformationTrustedDomain PROTO :DWORD,:DWORD,:DWORD
LsarSetQuotasForAccount PROTO :DWORD,:DWORD
LsarSetSecret PROTO :DWORD,:DWORD,:DWORD
LsarSetSecurityObject PROTO :DWORD,:DWORD,:DWORD
LsarSetSystemAccessAccount PROTO :DWORD,:DWORD
LsarSetTrustedDomainInfoByName PROTO :DWORD,:DWORD,:DWORD,:DWORD
ServiceInit PROTO 

ELSE
echo -----------------------------------------
echo WARNING Duplicate include file lsasrv.inc
echo -----------------------------------------
ENDIF
