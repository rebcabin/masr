; ModuleID = 'LFortran'
source_filename = "LFortran"

__AT__0 = private unnamed_addr constant [2 x i8] c" \00", align 1
__AT__1 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1
__AT__2 = private unnamed_addr constant [5 x i8] c"%d%s\00", align 1

define void __AT____module__global_symbols__lpython_main_program() !dbg !3 {
.:entry
  call void __AT____module__global_symbols_main0(), !dbg !6
  br label %return, !dbg !6

:return                                           ; preds = %.entry
  ret void, !dbg !6
}

define void __AT____module__global_symbols_main0() !dbg !7 {
.:entry
  %x = alloca i32, align 4, !dbg !8
  call void __AT__llvm.dbg.declare(metadata i32* %x, metadata !9, metadata !DIExpression()), !dbg !11
  %x2 = alloca i64, align 8
  call void __AT__llvm.dbg.declare(metadata i64* %x2, metadata !12, metadata !DIExpression()), !dbg !14
  %y = alloca float, align 4
  call void __AT__llvm.dbg.declare(metadata float* %y, metadata !15, metadata !DIExpression()), !dbg !17
  %y2 = alloca double, align 8
  call void __AT__llvm.dbg.declare(metadata double* %y2, metadata !18, metadata !DIExpression()), !dbg !20
  store i32 25, i32* %x, align 4, !dbg !21
  %0 = load i32, i32* %x, align 4, !dbg !21
  call void (i8*, ...) __AT___lfortran_printf(i8* getelementptr inbounds ([5 x i8], [5 x i8]* __AT__2, i32 0, i32 0), i32 %0, i8* getelementptr inbounds ([2 x i8], [2 x i8]* __AT__1, i32 0, i32 0)), !dbg !21
  br label %return, !dbg !21

:return                                           ; preds = %.entry
  ret void, !dbg !21
}

; Function :Attrs nounwind readnone speculatable willreturn
declare void __AT__llvm.dbg.declare(metadata, metadata, metadata) #0

declare void __AT___lfortran_printf(i8*, ...)

define i32 __AT__main(i32 %0, i8** %1) !dbg !22 {
.:entry
  call void __AT___lpython_set_argv(i32 %0, i8** %1), !dbg !25
  call void __AT____module__global_symbols__lpython_main_program(), !dbg !25
  ret i32 0, !dbg !25
}

declare void __AT___lpython_set_argv(i32, i8**)

attributes #0 = { nounwind readnone speculatable willreturn }

!llvm.dbg.cu = !{!0}

!0 = distinct !DICompileUnit(:language DW_LANG_C, :file !1, :producer "LPython Compiler", :isOptimized false, :runtimeVersion 0, :emissionKind FullDebug, :enums !2)
!1 = !DIFile(:filename "tests/expr_01.py", :directory ".")
!2 = !{}
!3 = distinct !DISubprogram(:name "_lpython_main_program", :scope !1, :file !1, :line 1, :type !4, :flags DIFlagPrototyped, :spFlags DISPFlagDefinition, :unit !0, :retainedNodes !2)
!4 = !DISubroutineType(:types !5)
!5 = !{null}
!6 = !DILocation(:line 9, :column 1, :scope !3)
!7 = distinct !DISubprogram(:name "main0", :scope !1, :file !1, :line 1, :type !4, :flags DIFlagPrototyped, :spFlags DISPFlagDefinition, :unit !0, :retainedNodes !2)
!8 = !DILocation(:line 1, :column 1, :scope !7)
!9 = !DILocalVariable(:name "x", :arg 1, :scope !7, :file !1, :line 2, :type !10)
!10 = !DIBasicType(:name "integer", :size 32, :encoding DW_ATE_signed)
!11 = !DILocation(:line 2, :scope !7)
!12 = !DILocalVariable(:name "x2", :arg 2, :scope !7, :file !1, :line 3, :type !13)
!13 = !DIBasicType(:name "integer", :size 64, :encoding DW_ATE_signed)
!14 = !DILocation(:line 3, :scope !7)
!15 = !DILocalVariable(:name "y", :arg 3, :scope !7, :file !1, :line 4, :type !16)
!16 = !DIBasicType(:name "float", :size 32, :encoding DW_ATE_float)
!17 = !DILocation(:line 4, :scope !7)
!18 = !DILocalVariable(:name "y2", :arg 4, :scope !7, :file !1, :line 5, :type !19)
!19 = !DIBasicType(:name "double", :size 64, :encoding DW_ATE_float)
!20 = !DILocation(:line 5, :scope !7)
!21 = !DILocation(:line 6, :column 5, :scope !7)
!22 = distinct !DISubprogram(:name "main_program", :scope !1, :file !1, :line 1, :type !23, :flags DIFlagPrototyped, :spFlags DISPFlagDefinition, :unit !0, :retainedNodes !2)
!23 = !DISubroutineType(:types !24)
!24 = !{!10}
!25 = !DILocation(:line 1, :column 1, :scope !22)
