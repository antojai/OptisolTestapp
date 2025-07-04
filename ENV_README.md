# Environment Configuration Guide

This document explains how to use the environment files in this Android project.

## Overview

The project now includes environment-specific configuration files to manage different deployment environments and externalize sensitive configuration values.

## Environment Files

### `.env`
The main environment file containing current configuration values. This file contains the actual values extracted from your codebase.

### `.env.example`
A template file showing all available environment variables with placeholder values. Use this as a reference when setting up new environments.

### `.env.development`
Configuration for development environment with debug settings enabled.

### `.env.staging`
Configuration for staging environment with some production-like settings but still allowing debugging.

### `.env.production`
Configuration for production environment with security and performance optimizations.

## Key Configuration Values

### API Configuration
- `API_BASE_URL`: Base URL for your API server
- `API_BEARER_TOKEN`: Authorization token for API requests
- `API_CONNECT_TIMEOUT`, `API_READ_TIMEOUT`, `API_WRITE_TIMEOUT`: Network timeout settings

### Application Settings
- `DEFAULT_USER_ID`: Default user ID for API calls
- `DEFAULT_LATITUDE`, `DEFAULT_LONGITUDE`: Default location coordinates
- `APP_ENVIRONMENT`: Current environment (development/staging/production)
- `DEBUG_ENABLED`: Enable/disable debug logging

### Build Configuration
- `APPLICATION_ID`: Android package name
- `VERSION_CODE`, `VERSION_NAME`: App version information
- SDK version settings

## Current Hardcoded Values Found

The following values were found hardcoded in your source code and should be replaced with environment variables:

1. **API Base URL**: `http://35.164.28.104:8080/alertmobile/` (in ApiClient.java)
2. **Bearer Token**: `044f910c-f202-482c-9119-72509e6f838a` (in ApiInterface.java)
3. **User ID**: `623` (in MainActivity.java)
4. **Coordinates**: `13.0827, 80.2707` (in MainActivity.java)

## Usage Instructions

### 1. Choose Your Environment File
Copy the appropriate environment file to `.env`:

```bash
# For development
cp .env.development .env

# For staging
cp .env.staging .env

# For production
cp .env.production .env
```

### 2. Update Configuration Values
Edit the `.env` file and replace placeholder values with your actual configuration:

```bash
# Example: Update API configuration
API_BASE_URL=https://your-actual-api-server.com/alertmobile/
API_BEARER_TOKEN=your-actual-bearer-token
```

### 3. Integrate with Build System
To use these environment variables in your Android project, you'll need to:

#### Option A: Use Gradle Properties
Add to your `app/build.gradle`:

```gradle
android {
    defaultConfig {
        // Load environment variables
        Properties envProps = new Properties()
        envProps.load(new FileInputStream(rootProject.file('.env')))
        
        buildConfigField "String", "API_BASE_URL", "\"${envProps.getProperty('API_BASE_URL')}\""
        buildConfigField "String", "API_BEARER_TOKEN", "\"${envProps.getProperty('API_BEARER_TOKEN')}\""
        buildConfigField "int", "DEFAULT_USER_ID", "${envProps.getProperty('DEFAULT_USER_ID')}"
        buildConfigField "double", "DEFAULT_LATITUDE", "${envProps.getProperty('DEFAULT_LATITUDE')}"
        buildConfigField "double", "DEFAULT_LONGITUDE", "${envProps.getProperty('DEFAULT_LONGITUDE')}"
    }
}
```

#### Option B: Use a Gradle Plugin
Add the `dotenv-gradle` plugin to your `build.gradle`:

```gradle
plugins {
    id 'co.uzzu.dotenv.gradle' version '2.0.0'
}
```

### 4. Update Source Code
Replace hardcoded values in your Java files:

#### ApiClient.java
```java
public static final String BASE_URL = BuildConfig.API_BASE_URL;
```

#### ApiInterface.java
```java
@Headers("Authorization: Bearer " + BuildConfig.API_BEARER_TOKEN)
```

#### MainActivity.java
```java
Call<GetallpopularalertsResponse> call = apiService.getalldetails(
    BuildConfig.DEFAULT_USER_ID,
    BuildConfig.DEFAULT_LATITUDE, 
    BuildConfig.DEFAULT_LONGITUDE
);
```

## Security Best Practices

1. **Never commit sensitive `.env` files** to version control
2. **Add `.env` to your `.gitignore`** file
3. **Use different tokens** for different environments
4. **Rotate tokens regularly** in production
5. **Use HTTPS** for production API endpoints
6. **Enable certificate pinning** in production

## Git Ignore Configuration

Add these lines to your `.gitignore`:

```gitignore
# Environment files with sensitive data
.env
.env.local
.env.*.local

# Keep template files
!.env.example
!.env.development
!.env.staging
!.env.production
```

## Environment Switching

For easy environment switching, you can create scripts:

### `switch-to-dev.sh`
```bash
#!/bin/bash
cp .env.development .env
echo "Switched to development environment"
```

### `switch-to-staging.sh`
```bash
#!/bin/bash
cp .env.staging .env
echo "Switched to staging environment"
```

### `switch-to-prod.sh`
```bash
#!/bin/bash
cp .env.production .env
echo "Switched to production environment"
```

## Validation

Before building, ensure your `.env` file contains all required variables:

```bash
# Check if required variables are set
grep -q "API_BASE_URL=" .env && echo "✓ API_BASE_URL set" || echo "✗ API_BASE_URL missing"
grep -q "API_BEARER_TOKEN=" .env && echo "✓ API_BEARER_TOKEN set" || echo "✗ API_BEARER_TOKEN missing"
```

## Troubleshooting

### Common Issues

1. **Build fails with missing BuildConfig fields**
   - Ensure your `.env` file exists and contains all required variables
   - Check that your `build.gradle` properly loads the environment file

2. **API calls fail with 401 Unauthorized**
   - Verify your `API_BEARER_TOKEN` is correct for the target environment
   - Check that the token hasn't expired

3. **Wrong API endpoint**
   - Confirm `API_BASE_URL` matches your target environment
   - Ensure the URL includes the correct protocol (http/https)

### Getting Help

If you encounter issues:
1. Check that your `.env` file format matches the examples
2. Verify all required environment variables are set
3. Test with the development environment first
4. Check Android Studio's build logs for specific error messages

## Next Steps

1. **Implement environment variable loading** in your build system
2. **Update source code** to use BuildConfig values instead of hardcoded strings
3. **Test each environment** to ensure proper configuration
4. **Set up CI/CD** to use appropriate environment files for different deployment stages
5. **Document any additional** environment-specific configurations your team needs
