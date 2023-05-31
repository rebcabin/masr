(module
    (type (;0;) (func (param i32) (result)))
    (type (;1;) (func (param i32 i32 i32 i32) (result i32)))
    (type (;2;) (func (param) (result)))
    (type (;3;) (func (param) (result)))
    (type (;4;) (func (param i32) (result i32)))
    (type (;5;) (func (param i32) (result i32)))
    (type (;6;) (func (param i32) (result i64)))
    (type (;7;) (func (param) (result)))
    (import "wasi_snapshot_preview1" "proc_exit" (func (;0;) (type 0)))
    (import "wasi_snapshot_preview1" "fd_write" (func (;1;) (type 1)))
    (global $0 (mut i32) (i32.const 0))
    (global $1 (mut i32) (i32.const 0))
    (global $2 (mut i64) (i64.const 0))
    (global $3 (mut f32) (f32.const 0.000000))
    (global $4 (mut f64) (f64.const 0.000000))
    (func $2 (type 2) (param) (result)
        (local)
        call 3
        return
    )
    (func $3 (type 3) (param) (result)
        (local i32 i64)
        i32.const 4
        call 4
        local.set 0
        i32.const 4
        call 5
        local.set 0
        i32.const 5
        call 6
        local.set 1
        return
    )
    (func $4 (type 4) (param i32) (result i32)
        (local i32 i32)
        local.get 0
        i32.const 0
        i32.lt_s
        if
            i32.const 0
            local.set 1
            local.get 1
            return
        else
        end
        i32.const 1
        local.set 2
        loop
            local.get 0
            i32.const 0
            i32.gt_s
            if
                local.get 2
                local.get 0
                i32.mul
                local.set 2
                local.get 0
                i32.const 1
                i32.sub
                local.set 0
                br 1
            else
            end
        end
        local.get 2
        local.set 1
        local.get 1
        return
    )
    (func $5 (type 5) (param i32) (result i32)
        (local i32 i32 i32)
        i32.const 1
        local.set 3
        i32.const 1
        i32.const 1
        i32.sub
        local.set 2
        loop
            local.get 2
            i32.const 1
            i32.add
            local.get 0
            i32.const 1
            i32.add
            i32.const 1
            i32.sub
            i32.le_s
            if
                local.get 2
                i32.const 1
                i32.add
                local.set 2
                local.get 3
                local.get 2
                i32.mul
                local.set 3
                br 1
            else
            end
        end
        local.get 3
        local.set 1
        local.get 1
        return
    )
    (func $6 (type 6) (param i32) (result i64)
        (local i64 i64)
        i64.const 0
        local.set 2
        local.get 0
        i32.const 0
        i32.lt_s
        if
            local.get 2
            local.set 1
            local.get 1
            return
        else
        end
        i64.const 1
        local.set 2
        loop
            local.get 0
            i32.const 0
            i32.gt_s
            if
                local.get 2
                local.get 0
                i64.extend_i32_s
                i64.mul
                local.set 2
                local.get 0
                i32.const 1
                i32.sub
                local.set 0
                br 1
            else
            end
        end
        local.get 2
        local.set 1
        local.get 1
        return
    )
    (func $7 (type 7) (param) (result)
        (local)
        call 2
        i32.const 0
        call 0
        return
    )
    (memory (;0;) 100 100)
    (export "memory" (memory 0))
    (export "_lpython_main_program" (func 2))
    (export "main0" (func 3))
    (export "test_factorial_1" (func 4))
    (export "test_factorial_2" (func 5))
    (export "test_factorial_3" (func 6))
    (export "_start" (func 7))
    (data (;0;) (i32.const 4) "\0c\00\00\00\01\00\00\00")
    (data (;1;) (i32.const 12) "    ")
    (data (;2;) (i32.const 16) "\18\00\00\00\01\00\00\00")
    (data (;3;) (i32.const 24) "\0a   ")
    (data (;4;) (i32.const 28) "\24\00\00\00\01\00\00\00")
    (data (;5;) (i32.const 36) "-   ")
    (data (;6;) (i32.const 40) "\30\00\00\00\01\00\00\00")
    (data (;7;) (i32.const 48) ".   ")
    (data (;8;) (i32.const 52) "\3c\00\00\00\01\00\00\00")
    (data (;9;) (i32.const 60) "(   ")
    (data (;10;) (i32.const 64) "\48\00\00\00\01\00\00\00")
    (data (;11;) (i32.const 72) ")   ")
    (data (;12;) (i32.const 76) "\54\00\00\00\01\00\00\00")
    (data (;13;) (i32.const 84) ",   ")
    (data (;14;) (i32.const 88) "\60\00\00\00\01\00\00\00")
    (data (;15;) (i32.const 96) "0   ")
    (data (;16;) (i32.const 100) "\6c\00\00\00\01\00\00\00")
    (data (;17;) (i32.const 108) "1   ")
    (data (;18;) (i32.const 112) "\78\00\00\00\01\00\00\00")
    (data (;19;) (i32.const 120) "2   ")
    (data (;20;) (i32.const 124) "\84\00\00\00\01\00\00\00")
    (data (;21;) (i32.const 132) "3   ")
    (data (;22;) (i32.const 136) "\90\00\00\00\01\00\00\00")
    (data (;23;) (i32.const 144) "4   ")
    (data (;24;) (i32.const 148) "\9c\00\00\00\01\00\00\00")
    (data (;25;) (i32.const 156) "5   ")
    (data (;26;) (i32.const 160) "\a8\00\00\00\01\00\00\00")
    (data (;27;) (i32.const 168) "6   ")
    (data (;28;) (i32.const 172) "\b4\00\00\00\01\00\00\00")
    (data (;29;) (i32.const 180) "7   ")
    (data (;30;) (i32.const 184) "\c0\00\00\00\01\00\00\00")
    (data (;31;) (i32.const 192) "8   ")
    (data (;32;) (i32.const 196) "\cc\00\00\00\01\00\00\00")
    (data (;33;) (i32.const 204) "9   ")
)
