# remove target folder
rm -rf target

# create target folder
mkdir target

# compiling .class files into a specified folder
javac -d ./target ./src/java/edu.school21.printer/*/*.java

# execute .jar file with arguments
java -cp target edu.school21.printer.app.Program . 0 /Users/prranges/IdeaProjects/java_piscine/day04/ex00/ImagesToChar/it.bmp
