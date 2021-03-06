  ; ===========================================
  ; win32k.inc copyright MASM32 1998 - 2005
  ; ===========================================

IFNDEF WIN32K_INC
WIN32K_INC equ <1>

BRUSHOBJ_hGetColorTransform PROTO :DWORD
BRUSHOBJ_pvAllocRbrush PROTO :DWORD,:DWORD
BRUSHOBJ_pvGetRbrush PROTO :DWORD
BRUSHOBJ_ulGetBrushColor PROTO :DWORD
CLIPOBJ_bEnum PROTO :DWORD,:DWORD,:DWORD
CLIPOBJ_cEnumStart PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
CLIPOBJ_ppoGetPath PROTO :DWORD
EngAcquireSemaphore PROTO :DWORD
EngAllocMem PROTO :DWORD,:DWORD,:DWORD
EngAllocPrivateUserMem PROTO :DWORD,:DWORD,:DWORD
EngAllocSectionMem PROTO :DWORD,:DWORD,:DWORD,:DWORD
EngAllocUserMem PROTO :DWORD,:DWORD
EngAlphaBlend PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngAssociateSurface PROTO :DWORD,:DWORD,:DWORD
EngBitBlt PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngBugCheckEx PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngCheckAbort PROTO :DWORD
EngClearEvent PROTO :DWORD
EngComputeGlyphSet PROTO :DWORD,:DWORD,:DWORD
EngControlSprites PROTO :DWORD,:DWORD
EngCopyBits PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngCreateBitmap PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngCreateClip PROTO 
EngCreateDeviceBitmap PROTO :DWORD,:DWORD,:DWORD,:DWORD
EngCreateDeviceSurface PROTO :DWORD,:DWORD,:DWORD,:DWORD
EngCreateDriverObj PROTO :DWORD,:DWORD,:DWORD
EngCreateEvent PROTO :DWORD
EngCreatePalette PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngCreatePath PROTO 
EngCreateSemaphore PROTO 
EngCreateWnd PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngDebugBreak PROTO 
EngDebugPrint PROTO :DWORD,:DWORD,:DWORD
EngDeleteClip PROTO :DWORD
EngDeleteDriverObj PROTO :DWORD,:DWORD,:DWORD
EngDeleteEvent PROTO :DWORD
EngDeleteFile PROTO :DWORD
EngDeletePalette PROTO :DWORD
EngDeletePath PROTO :DWORD
EngDeleteSafeSemaphore PROTO :DWORD
EngDeleteSemaphore PROTO :DWORD
EngDeleteSurface PROTO :DWORD
EngDeleteWnd PROTO :DWORD
EngDeviceIoControl PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngDitherColor PROTO :DWORD,:DWORD,:DWORD,:DWORD
EngDxIoctl PROTO :DWORD,:DWORD,:DWORD
EngEnumForms PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngEraseSurface PROTO :DWORD,:DWORD,:DWORD
EngFileIoControl PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngFileWrite PROTO :DWORD,:DWORD,:DWORD,:DWORD
EngFillPath PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngFindImageProcAddress PROTO :DWORD,:DWORD
EngFindResource PROTO :DWORD,:DWORD,:DWORD,:DWORD
EngFntCacheAlloc PROTO :DWORD,:DWORD
EngFntCacheFault PROTO :DWORD,:DWORD
EngFntCacheLookUp PROTO :DWORD,:DWORD
EngFreeMem PROTO :DWORD
EngFreeModule PROTO :DWORD
EngFreePrivateUserMem PROTO :DWORD,:DWORD
EngFreeSectionMem PROTO :DWORD,:DWORD
EngFreeUserMem PROTO :DWORD
EngGetCurrentCodePage PROTO :DWORD,:DWORD
EngGetCurrentProcessId PROTO 
EngGetCurrentThreadId PROTO 
EngGetDriverName PROTO :DWORD
EngGetFileChangeTime PROTO :DWORD,:DWORD
EngGetFilePath PROTO :DWORD,:DWORD
EngGetForm PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngGetLastError PROTO 
EngGetPrinter PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngGetPrinterData PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngGetPrinterDataFileName PROTO :DWORD
EngGetPrinterDriver PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngGetProcessHandle PROTO 
EngGetTickCount PROTO 
EngGetType1FontList PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngGradientFill PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngHangNotification PROTO :DWORD,:DWORD
EngInitializeSafeSemaphore PROTO :DWORD
EngIsSemaphoreOwned PROTO :DWORD
EngIsSemaphoreOwnedByCurrentThread PROTO :DWORD
EngLineTo PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngLoadImage PROTO :DWORD
EngLoadModule PROTO :DWORD
EngLoadModuleForWrite PROTO :DWORD,:DWORD
EngLockDirectDrawSurface PROTO :DWORD
EngLockDriverObj PROTO :DWORD
EngLockSurface PROTO :DWORD
EngLpkInstalled PROTO 
EngMapEvent PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngMapFile PROTO :DWORD,:DWORD,:DWORD
EngMapFontFile PROTO :DWORD,:DWORD,:DWORD
EngMapFontFileFD PROTO :DWORD,:DWORD,:DWORD
EngMapModule PROTO :DWORD,:DWORD
EngMapSection PROTO :DWORD,:DWORD,:DWORD,:DWORD
EngMarkBandingSurface PROTO :DWORD
EngModifySurface PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngMovePointer PROTO :DWORD,:DWORD,:DWORD,:DWORD
EngMulDiv PROTO :DWORD,:DWORD,:DWORD
EngMultiByteToUnicodeN PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngMultiByteToWideChar PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngNineGrid PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngPaint PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngPlgBlt PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngProbeForRead PROTO :DWORD,:DWORD,:DWORD
EngProbeForReadAndWrite PROTO :DWORD,:DWORD,:DWORD
EngQueryDeviceAttribute PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngQueryLocalTime PROTO :DWORD
EngQueryPalette PROTO :DWORD,:DWORD,:DWORD,:DWORD
EngQueryPerformanceCounter PROTO :DWORD
EngQueryPerformanceFrequency PROTO :DWORD
EngQuerySystemAttribute PROTO :DWORD,:DWORD
EngReadStateEvent PROTO :DWORD
EngReleaseSemaphore PROTO :DWORD
EngRestoreFloatingPointState PROTO :DWORD
EngSaveFloatingPointState PROTO :DWORD,:DWORD
EngSecureMem PROTO :DWORD,:DWORD
EngSetEvent PROTO :DWORD
EngSetLastError PROTO :DWORD
EngSetPointerShape PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngSetPointerTag PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngSetPrinterData PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngSort PROTO :DWORD,:DWORD,:DWORD,:DWORD
EngStretchBlt PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngStretchBltROP PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngStrokeAndFillPath PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngStrokePath PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngTextOut PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngTransparentBlt PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngUnicodeToMultiByteN PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngUnloadImage PROTO :DWORD
EngUnlockDirectDrawSurface PROTO :DWORD
EngUnlockDriverObj PROTO :DWORD
EngUnlockSurface PROTO :DWORD
EngUnmapEvent PROTO :DWORD
EngUnmapFile PROTO :DWORD
EngUnmapFontFile PROTO :DWORD
EngUnmapFontFileFD PROTO :DWORD
EngUnsecureMem PROTO :DWORD
EngWaitForSingleObject PROTO :DWORD,:DWORD
EngWideCharToMultiByte PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
EngWritePrinter PROTO :DWORD,:DWORD,:DWORD,:DWORD
FLOATOBJ_Add PROTO :DWORD,:DWORD
FLOATOBJ_AddFloat PROTO :DWORD,:DWORD
FLOATOBJ_AddLong PROTO :DWORD,:DWORD
FLOATOBJ_Div PROTO :DWORD,:DWORD
FLOATOBJ_DivFloat PROTO :DWORD,:DWORD
FLOATOBJ_DivLong PROTO :DWORD,:DWORD
FLOATOBJ_Equal PROTO :DWORD,:DWORD
FLOATOBJ_EqualLong PROTO :DWORD,:DWORD
FLOATOBJ_GetFloat PROTO :DWORD
FLOATOBJ_GetLong PROTO :DWORD
FLOATOBJ_GreaterThan PROTO :DWORD,:DWORD
FLOATOBJ_GreaterThanLong PROTO :DWORD,:DWORD
FLOATOBJ_LessThan PROTO :DWORD,:DWORD
FLOATOBJ_LessThanLong PROTO :DWORD,:DWORD
FLOATOBJ_Mul PROTO :DWORD,:DWORD
FLOATOBJ_MulFloat PROTO :DWORD,:DWORD
FLOATOBJ_MulLong PROTO :DWORD,:DWORD
FLOATOBJ_Neg PROTO :DWORD
FLOATOBJ_SetFloat PROTO :DWORD,:DWORD
FLOATOBJ_SetLong PROTO :DWORD,:DWORD
FLOATOBJ_Sub PROTO :DWORD,:DWORD
FLOATOBJ_SubFloat PROTO :DWORD,:DWORD
FLOATOBJ_SubLong PROTO :DWORD,:DWORD
FONTOBJ_cGetAllGlyphHandles PROTO :DWORD,:DWORD
FONTOBJ_cGetGlyphs PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
FONTOBJ_pQueryGlyphAttrs PROTO :DWORD,:DWORD
FONTOBJ_pfdg PROTO :DWORD
FONTOBJ_pifi PROTO :DWORD
FONTOBJ_pjOpenTypeTablePointer PROTO :DWORD,:DWORD,:DWORD
FONTOBJ_pvTrueTypeFontFile PROTO :DWORD,:DWORD
FONTOBJ_pwszFontFilePaths PROTO :DWORD,:DWORD
FONTOBJ_pxoGetXform PROTO :DWORD
FONTOBJ_vGetInfo PROTO :DWORD,:DWORD,:DWORD
HT_ComputeRGBGammaTable PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
HT_Get8BPPFormatPalette PROTO :DWORD,:DWORD,:DWORD,:DWORD
HT_Get8BPPMaskPalette PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD,:DWORD
HeapVidMemAllocAligned PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
PALOBJ_cGetColors PROTO :DWORD,:DWORD,:DWORD,:DWORD
PATHOBJ_bCloseFigure PROTO :DWORD
PATHOBJ_bEnum PROTO :DWORD,:DWORD
PATHOBJ_bEnumClipLines PROTO :DWORD,:DWORD,:DWORD
PATHOBJ_bMoveTo PROTO :DWORD,:DWORD,:DWORD
PATHOBJ_bPolyBezierTo PROTO :DWORD,:DWORD,:DWORD
PATHOBJ_bPolyLineTo PROTO :DWORD,:DWORD,:DWORD
PATHOBJ_vEnumStart PROTO :DWORD
PATHOBJ_vEnumStartClipLines PROTO :DWORD,:DWORD,:DWORD,:DWORD
PATHOBJ_vGetBounds PROTO :DWORD,:DWORD
RtlAnsiCharToUnicodeChar PROTO :DWORD
RtlMultiByteToUnicodeN PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
RtlRaiseException PROTO :DWORD
RtlUnicodeToMultiByteN PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
RtlUnicodeToMultiByteSize PROTO :DWORD,:DWORD,:DWORD
RtlUnwind PROTO :DWORD,:DWORD,:DWORD,:DWORD
RtlUpcaseUnicodeChar PROTO :DWORD
RtlUpcaseUnicodeToMultiByteN PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
STROBJ_bEnum PROTO :DWORD,:DWORD,:DWORD
STROBJ_bEnumPositionsOnly PROTO :DWORD,:DWORD,:DWORD
STROBJ_bGetAdvanceWidths PROTO :DWORD,:DWORD,:DWORD,:DWORD
STROBJ_dwGetCodePage PROTO :DWORD
STROBJ_fxBreakExtra PROTO :DWORD
STROBJ_fxCharacterExtra PROTO :DWORD
STROBJ_vEnumStart PROTO :DWORD
VidMemFree PROTO :DWORD,:DWORD
WNDOBJ_bEnum PROTO :DWORD,:DWORD,:DWORD
WNDOBJ_cEnumStart PROTO :DWORD,:DWORD,:DWORD,:DWORD
WNDOBJ_vSetConsumer PROTO :DWORD,:DWORD
XFORMOBJ_bApplyXform PROTO :DWORD,:DWORD,:DWORD,:DWORD,:DWORD
XFORMOBJ_iGetFloatObjXform PROTO :DWORD,:DWORD
XFORMOBJ_iGetXform PROTO :DWORD,:DWORD
XLATEOBJ_cGetPalette PROTO :DWORD,:DWORD,:DWORD,:DWORD
XLATEOBJ_hGetColorTransform PROTO :DWORD
XLATEOBJ_iXlate PROTO :DWORD,:DWORD
XLATEOBJ_piVector PROTO :DWORD

ELSE
echo -----------------------------------------
echo WARNING Duplicate include file win32k.inc
echo -----------------------------------------
ENDIF
