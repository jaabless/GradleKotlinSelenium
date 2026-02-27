@echo off
REM Gradle wrapper script for Windows
REM This script will run Gradle tasks

setlocal enabledelayedexpansion

REM Get the script directory
set SCRIPT_DIR=%~dp0

REM Check if gradlew.bat exists
if exist "%SCRIPT_DIR%gradlew.bat" (
    call "%SCRIPT_DIR%gradlew.bat" %*
) else if exist "%SCRIPT_DIR%gradlew" (
    bash "%SCRIPT_DIR%gradlew" %*
) else (
    REM Try to find gradle in PATH
    where gradle >nul 2>nul
    if !errorlevel! equ 0 (
        gradle %*
    ) else (
        echo Gradle not found. Please install Gradle or run gradlew.bat
        exit /b 1
    )
)

endlocal

