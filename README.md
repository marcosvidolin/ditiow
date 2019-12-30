# ditiow🦸
Ditiow is an aspect library designed to help you safely expose features of your Spring REST API without having to expose data from the persistence or business layer of your application.

[ ![Download](https://api.bintray.com/packages/marcosvidolin/maven/ditiow/images/download.svg?version=1.0.0) ](https://bintray.com/marcosvidolin/maven/ditiow/1.0.0/link) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/5f79c15a8aa44706afcf49261c1a6ef1)](https://www.codacy.com/manual/marcosvidolin/ditiow?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=marcosvidolin/ditiow&amp;utm_campaign=Badge_Grade) [![Build Status](https://travis-ci.org/marcosvidolin/copy-properties-assembler.svg?branch=master)](https://travis-ci.org/marcosvidolin/copy-properties-assembler)

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
    public DitiowAspect ditiow() {
        return new DitiowAspect();
    }
    ```

## How to use

JPA/Entity class with all fields. **Nothing needs to be done at this point**.

```java
@Entity
@Table(name = "post")
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "POST_CD_POST", unique = true)
  private Long code;

  @Type(type="org.hibernate.type.UUIDCharType")
  @Column(name = "POST_CD_UUID", unique = true, updatable = false)
  private UUID uuid;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(unique = true, updatable = false)
  private User author;

  @Column(name = "POST_TX_CONTENT")
  private String content;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "POST_DT_CREATE", updatable = false)
  private Date publishedAt;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private Collection<Comment> comments;
  
  // ...
  
}
```

### Returning a resource from the controller

- Here we specify the resource or how our Post object will be exposed by the API. 
Note that the name of the attributes are the same as Post. 

    *But I don't want to expose some attributes like database id and comments, for example.*

    ```java
    public class PostGetResource {
      private UUID uuid;
      private User author;
      private String content;
      private Date publishedAt;
      // ...
    }
    ```

- Enable conversion by adding @ResourseResponse annotation on controller class with the "PostGetResource" as the value of the annotation.

    ```java
      @GetMapping(path = "/posts/{uuid}")
      @ResponseResource(PostGetResource.class)
      public ResponseEntity<?> get(@PathVariable UUID uuid) {
        Post post = this.postBusiness.findPostByUuid(uuid);
        return ResponseEntity.ok(post);
      }
    ```

     Here the magic happens. The response will be converted to a PostGetResourse object that is inserted into the body of the ResponseEntity object. 

### Retrieving a resource as a parameter

```java
public class PostCreateResource extends AbstractResource<Post> {
  private UUID uuid;
  @NotEmpty
  @Length(min = 50, max = 300)
  private String content;
  public PostCreateResource() {
    super(Post.class);
  }
  // ...
}
```

```java
  @PostMapping(path = "/posts")
  @ResponseResource(PostGetResource.class)
  public ResponseEntity<?> create(@Valid @RequestBody PostCreateResource resource) {
    Post post = resource.toBusiness();
    post.setAuthor(this.currentUserUtil.getUser());
    return ResponseEntity.ok(this.postBusiness.create(post));
  }
```

## TODO

- Create an annotation to specify the attribute name of the resource (without having to match the name of the business class);
- Unit tests; 

## Contributors

[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/images/0)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/links/0)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/images/1)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/links/1)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/images/2)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/links/2)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/images/3)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/links/3)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/images/4)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/links/4)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/images/5)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/links/5)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/images/6)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/links/6)[![](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/images/7)](https://sourcerer.io/fame/marcosvidolin/marcosvidolin/ditiow/links/7)
