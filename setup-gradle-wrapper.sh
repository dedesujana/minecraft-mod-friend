#!/bin/bash

# Create gradle wrapper directories
mkdir -p gradle/wrapper

# Download gradle-wrapper.jar
curl -L https://github.com/gradle/gradle/releases/download/v9.5.1/gradle-9.5.1-bin.zip -o gradle-9.5.1-bin.zip
unzip -q gradle-9.5.1-bin.zip
cp gradle-9.5.1/lib/plugins/gradle-wrapper-*.jar gradle/wrapper/gradle-wrapper.jar

# Create gradle-wrapper.properties
cat > gradle/wrapper/gradle-wrapper.properties << 'EOF'
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-9.5.1-bin.zip
networkTimeout=10000
validateDistributionUrl=true
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
EOF

# Cleanup
rm -rf gradle-9.5.1 gradle-9.5.1-bin.zip

echo "Gradle wrapper setup complete!"
