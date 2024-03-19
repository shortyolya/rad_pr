FROM openjdk
COPY . .
WORKDIR /.
EXPOSE 8001
RUN javac HelloWorldPrinter.java
CMD ["java", "HelloWorldPrinter"]
