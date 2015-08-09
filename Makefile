all: help

help:
	@echo ""
	@echo "-- Help Menu"
	@echo ""
	@echo "   1. make sonar-analysis        - perform sonar analysis"

sonar-analysis:
	@mvn sonar:sonar -Dsonar.host.url=http://localhost:51000 -Dsonar.jdbc.url=jdbc:postgresql://localhost:51100/sonar

