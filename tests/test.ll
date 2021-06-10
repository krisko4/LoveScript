declare i32 @printf(i8*, ...)
declare i32 @scanf(i8*, ...)
@strpi = constant [4 x i8] c"%d\0A\00"
@strpd = constant [4 x i8] c"%f\0A\00"
@strs = constant [3 x i8] c"%d\00"
@strps = constant [4 x i8] c"%s\0A\00"@strdd = constant [4 x i8] c"%lf\00"
@x = common dso_local global [5 x double] zeroinitializer, align 16
define i32 @main() nounwind{
%1 = getelementptr inbounds [5 x double],[5 x double]* @x, i64 0, i64 5
store double 3.5, double* %1
%2 = getelementptr inbounds [5 x double],[5 x double]* @x, i64 0, i64 2
store double 4.5, double* %2
%3 = load double, double* %2
%4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpd, i32 0, i32 0), double %3)
%5 = load double, double* %1
%6 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @strpd, i32 0, i32 0), double %5)
ret i32 0 }
