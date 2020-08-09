Link="https://docs.oracle.com/en/java/javase/13/docs/api"
Lib="libs/"
# shellcheck disable=SC2046
javadoc $(find . -name "*.java") -private -d "javadoc" -link "$Link" -p "$Lib"
