# Uganda-Gov-Java-Modules

Java Modules (JPMS) demo for the Government of Uganda e-services system (BIT2115).

Modules: gov.ug.core, gov.ug.nira, gov.ug.ura, gov.ug.immigration, gov.ug.ursb, gov.ug.portal

## Compile
```
javac -d out --module-source-path src $(find src -name "*.java")
```
## Run
```
java --module-path out -m gov.ug.portal/gov.ug.portal.GovPortal
```
