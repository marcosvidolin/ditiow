![ditiow](docs/assets/logo.png)

[ ![Download](https://api.bintray.com/packages/marcosvidolin/maven/ditiow/images/download.svg?version=1.1.0) ](https://bintray.com/marcosvidolin/maven/ditiow/1.0.0/link) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/5f79c15a8aa44706afcf49261c1a6ef1)](https://www.codacy.com/manual/marcosvidolin/ditiow?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=marcosvidolin/ditiow&amp;utm_campaign=Badge_Grade) ![Build](https://github.com/marcosvidolin/ditiow/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=master)

Ditiow is an aspect library designed to help you safely expose features of your Spring REST API without having to expose data from the persistence or business layer of your application.

## Setup

1. Ditiow is release by publishing in to the JCenter. So add the "jcenter" in your dependency management.

    - Gradle
    
    ```groovy      
	repositories {
	    jcenter()
	}
    ```
    
    - Maven
    
    ```xml
	<repositories>
		<repository>
			<id>jcenter</id>
			<name>jcenter</name>
			<url>https://jcenter.bintray.com</url>
		</repository>
	</repositories>
    ```

2. Declaring the dependency

    - Gradle
    
    ```groovy
    compile 'com.vidolima:ditiow:1.1.0'
    ```
   
   - Maven
   
   ```xml
   <dependency>
   	<groupId>com.vidolima</groupId>
   	<artifactId>ditiow</artifactId>
   	<version>1.1.0</version>
   	<type>pom</type>
   </dependency>
   ```
   
3. Declare DitiowAspect as a bean. Add aspect bean in one of the @Configuration classes

    ```java
    @Configuration
    public class DitiowConfig {
    
        @Bean
        public DitiowAspect ditiow() {
            return new DitiowAspect();
        }

    }
    ```

## How to use

Domain class with all fields. **Nothing needs to be done at this point**.

```java
public class Post {

  private Long code;
  private UUID uuid;
  private User author;
  private String content;
  private Date publishedAt;
  private Collection<Comment> comments;
  // ...
}
```

### Returning a resource from the controller

- Here we specify the resource or how our Post object will be exposed by the API.
We do this by extending the AbstractResource class and informing the domain class (Post) as type. 
Note that the name of the attributes are the same as Post.

    *But I don't want to expose some attributes like database id and comments, for example.*

    ```java
    public class PostGetResource extends AbstractResource<Post> {
      private UUID uuid;
      private User author;
      private String content;
      private Date publishedAt;
      // ...
    }
    ```

- Enable conversion by adding @ResponseResource annotation on controller class with the "PostGetResource" as the value of the annotation.

    ```java
      @GetMapping(path = "/posts/{uuid}")
      @ResponseResource(PostGetResource.class)
      public ResponseEntity<?> get(@PathVariable UUID uuid) {
        Post post = this.postService.findPostByUuid(uuid);
        return ResponseEntity.ok(post);
      }
    ```

     Here the magic happens. The response will be converted to a PostGetResource object that is inserted into the body of the ResponseEntity object. 

### Ignore the properties of a Resource at response time

- In this example the values of `author` and `publichedAt` fields will not be returned in the response

    *Whenever a primitive field is ignored, its original value will be omitted and the corresponding default value will be returned.*

```java
  @GetMapping(path = "/posts/{uuid}")
  @ResponseResource(PostGetResource.class, ignoreProperties = {"author", "publishedAt"})
  public ResponseEntity<?> get(@PathVariable UUID uuid) {
    Post post = this.postService.findPostByUuid(uuid);
    return ResponseEntity.ok(post);
  }
```
    
### Retrieving a resource as a parameter

```java
public class PostCreateResource extends AbstractResource<Post> {
  private UUID uuid;
  @NotEmpty
  @Length(min = 50, max = 300)
  private String content;
  // ...
}
```

```java
  @PostMapping(path = "/posts")
  @ResponseResource(PostGetResource.class)
  public ResponseEntity<?> create(@Valid @RequestBody PostCreateResource resource) {
    Post post = resource.toDomain(); // converts the resource into a Post
    post.setAuthor(this.currentUserUtil.getUser());
    return ResponseEntity.ok(this.postService.create(post));
  }
```

## Contributors

[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/images/0)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/links/0)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/images/1)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/links/1)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/images/2)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/links/2)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/images/3)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/links/3)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/images/4)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/links/4)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/images/5)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/links/5)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/images/6)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/links/6)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/images/7)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/links/7)
