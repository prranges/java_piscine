# remove target folder
rm -rf target

# create target folder
mkdir target

# compiling .class files into a specified folder
javac -d ./target ./src/java/edu.school21.printer/*/*.java

# coping resourses folder
cp -R ./src/resourses ./target

# create .jar archive
jar cfm ./target/images-to-chars-printer.jar ./src/manifest.txt -C ./target .

# adding executing rights to .jar file for user
chmod u+x ./target/images-to-chars-printer.jar

# execute .jar file with arguments
java -jar ./target/images-to-chars-printer.jar . 0
