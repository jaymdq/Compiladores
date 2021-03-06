  ; ===========================================
  ; query.inc copyright MASM32 1998 - 2005
  ; ===========================================

IFNDEF QUERY_INC
QUERY_INC equ <1>

AbortMerges PROTO :DWORD,:DWORD,:DWORD,:DWORD
BeginCacheTransaction PROTO :DWORD,:DWORD,:DWORD,:DWORD
BindIFilterFromStorage PROTO :DWORD,:DWORD,:DWORD
BindIFilterFromStream PROTO :DWORD,:DWORD,:DWORD
CIBuildQueryNode PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
CIBuildQueryTree PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
CICreateCommand PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
CIGetGlobalPropertyList PROTO :DWORD
CIMakeICommand PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
CIRestrictionToFullTree PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
CIState PROTO :DWORD,:DWORD,:DWORD
CITextToFullTree PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
CITextToFullTreeEx PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
CITextToSelectTree PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
CITextToSelectTreeEx PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
CiSvcMain PROTO :DWORD,:DWORD
CollectCIISAPIPerformanceData PROTO :DWORD,:DWORD,:DWORD,:DWORD
CollectCIPerformanceData PROTO :DWORD,:DWORD,:DWORD,:DWORD
CollectFILTERPerformanceData PROTO :DWORD,:DWORD,:DWORD,:DWORD
DoneCIISAPIPerformanceData PROTO 
DoneCIPerformanceData PROTO 
DoneFILTERPerformanceData PROTO 
EndCacheTransaction PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
ForceMasterMerge PROTO :DWORD,:DWORD,:DWORD,:DWORD
GetWin4ExceptionLevel PROTO 
InitializeCIISAPIPerformanceData PROTO :DWORD
InitializeCIPerformanceData PROTO :DWORD
InitializeFILTERPerformanceData PROTO :DWORD
LoadBHIFilter PROTO :DWORD,:DWORD,:DWORD,:DWORD
LoadBinaryFilter PROTO :DWORD,:DWORD
LoadIFilter PROTO :DWORD,:DWORD,:DWORD
LoadTextFilter PROTO :DWORD,:DWORD
LocateCatalogsA PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
LocateCatalogs equ <LocateCatalogsA>

LocateCatalogsW PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
SetCatalogState PROTO :DWORD,:DWORD,:DWORD,:DWORD
SetWin4AssertLevel PROTO :DWORD
SetWin4InfoLevel PROTO :DWORD
SetupCache PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
SetupCacheEx PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
StartFWCiSvcWork PROTO :DWORD,:DWORD,:DWORD
StopFWCiSvcWork PROTO :DWORD,:DWORD,:DWORD,:DWORD
SvcEntry_CiSvc PROTO :DWORD,:DWORD,:DWORD,:DWORD
Win4AssertEx PROTO :DWORD,:DWORD,:DWORD
vdprintf PROTO :DWORD,:DWORD,:DWORD,:DWORD

ELSE
echo -----------------------------------------
echo WARNING Duplicate include file query.inc
echo -----------------------------------------
ENDIF
