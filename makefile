all:
	@echo "Compiling..."
	javac *.java

run: 
	@echo "Running..."
	java JSketch

clean:
	rm -rf *.class
