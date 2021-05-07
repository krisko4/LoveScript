declare i32 @printf(i8*, ...)
declare i32 @scanf(i8*, ...)
@strpi = constant [4 x i8] c"%d\0A\00"
@strpd = constant [4 x i8] c"%f\0A\00"
@strs = constant [3 x i8] c"%d\00"
@strdd = constant [4 x i8] c"%lf\00"
@f = private constant [13 x i8] c"Hello world\0A\00"
@str25 = private constant [26 x i8] c"Kompilatory sa wspaniale\0A\00"
define i32 @main() nounwind{
%x = alloca double
%1 = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strdd, i32 0, i32 0), double* %x)
%2 = load double, double* %x
%3 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpd, i32 0, i32 0), double %2)
%4 = sitofp i32 5 to double
%5 = fadd double 5.5,%4
%6 = sitofp i32 -4 to double
%7 = sitofp i32 3 to double
%8 = fdiv double %6,%7
%9 = fmul double %8,%5
%10 = sitofp i32 3 to double
%11 = fdiv double 9.7,%10
%12 = fsub double %9,%11
%13 = fadd double 3.5,%12
%14 = load double, double* %x
%15 = fadd double %14,%13
%a = alloca double
store double %15, double* %a
%16 = load double, double* %a
%17 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpd, i32 0, i32 0), double %16)
%b = alloca double
store double 4.5, double* %b
%18 = fptosi double 4.5 to i32
%c = alloca i32
store i32 %18, i32* %c
%19 = load i32, i32* %c
%20 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpi, i32 0, i32 0), i32 %19)
%d = alloca i32
store i32 4, i32* %d
%21 = sitofp i32 4 to double
%e = alloca double
store double %21, double* %e
%22 = load double, double* %e
%23 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpd, i32 0, i32 0), double %22)
%24 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @f, i32 0, i32 0))
%25 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([26 x i8], [26 x i8]* @str25, i32 0, i32 0))
ret i32 0 }
