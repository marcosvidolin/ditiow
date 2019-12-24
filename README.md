# ditiow🦸
Ditiow is an aspect library designed to help you safely expose features of your Spring REST API without having to expose data from the persistence or business layer of your application.

## Setup

1. Add dependency

    - Gradle
    
    ```groovy
    compile 'com.vidolima:ditiow:1.1.0'
    ```
   
   - Maven
   
   ```xml
   <dependency>
   	<groupId>com.vidolima</groupId>
   	<artifactId>ditiow</artifactId>
   	<version>1.0.0</version>
   	<type>pom</type>
   </dependency>
   ```
   
2. Declare DitiowAspect as a bean. Add aspect bean in one of the @Configuration classes

    ```java
    @Bean
    public DitiowAspect ditiowAspect() {
        return new DitiowAspect();
    }
    ```

## How to use

1. Enable the conversion by adding @ResourseResponse annotation on controller classes(s)

    ```java
      @GetMapping(path = "/posts/{uuid}")
      @ResponseResource(PostGetResource.class)
      public ResponseEntity<?> get(@PathVariable UUID uuid) {
        Post post = this.postBusiness.findPostByUuid(uuid);
        return ResponseEntity.ok(post);
      }
    ```

## TODO

- Create an annotation to specify the attribute name of the resource (without having to match the name of the business class);
- Unit tests; 

## Contributors

