

https://blog.csdn.net/zhangbeizhen18/article/details/88866676

https://blog.csdn.net/andy_zhang2007/article/details/90632411

https://www.jianshu.com/p/fe1194ca8ecd

# 接口默认方法和静态方法

- Java 8允许我们给接口添加一个非抽象的方法实现，只需要使用 default关键字即可；
- 被default修饰的方法可以不用在接口的实现类直接使用
- 解决接口的修改与现有的实现不兼容的问题。
- 一个类实现多个接口，并且接口中有相同的默认方法时，要在类的默认方法中重新定义实现或者使用 super 来调用指定接口的默认方法
- 静态方法使用接口名称调用

```java
public interface Hero {

    String ultimateSkill(String name);

    default String skill() {
         return  "闪现";
    }
    static void showSkill2() {
        System.out.println("引燃");
    }
}
```

```java
public static void main(String[] args) {
        Hero kaiSa = new Hero() {
            @Override
            public String ultimateSkill(String name) {
                return name;
            }
        };
        kaiSa.ultimateSkill("猎手本能");
        kaiSa.skill();
     	Hero.showSkill2();
    }
```

# Lambda表达式（重要）

- Lammbda表达式**允许把函数作为一个方法的参数**，把函数作为参数传递到方法中，使代码变得更加简洁紧促，主要用来**定义行内执行的方法类型接口，免去了使用匿名方法的麻烦**，并且给予Java简单但是强大的函数化的编程能力。

- 需要向一个方法传递一个方法作为参数，但是实际上并不能传递方法，而是传递了只有一个抽象方法的接口的实现类的对象，这样就做到类似传递方法了，其实lanmada就是一个对象（）

- 语法

  ```java
  (parameters) -> expression或(parameters) ->{statements; }
  ```

  - 可选参数声明，编译器统一识别

  - 可选参数圆括号，多个参数时必须

  - 可选的大括号

  - 可选的返回关键字，如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定

  - Lambda 表达式只能引用标记了 final 的外层局部变量，不能在lambda 内部修改定义在域外的局部变量，否则会编译错误

  - lambda 表达式的局部变量可以不用声明为 final，但是不能被后面的代码修改（即隐性的具有final 的语义

  - 在Lambda 表达式当中不允许声明一个与局部变量同名的参数或者局部变量。

  - lambda内部对于实例的字段以及静态变量是即可读又可写
  
    ```java
           public class Lambda {
        static int a = 1;
    
        public static void main(String[] args) {
            List<String> chars = Arrays.asList("x", "y", "z");
            int num = 1;
    
            chars.forEach(System.out::println);
    
            chars.forEach(i -> {if (num == 1) {
                a = 5;
                System.out.println(i);
                }
            });
            //num = 2; //报错，num被隐式的被声明为final
            System.out.println(a);;
        }
    }
    ```

# 方法引用



- 方法引用通过方法的名字来指向一个方法，使得开发者可以直接引用现存的方法、Java类的构造方法或者实例对象。
  
- 方法引用和Lambda表达式配合使用，方法引用实际上是 Lambda 表达式的一种语法糖。
  
- 可以用方法引用的一定可以改写为 lambda 表达式，可以用 lambda 表达式的却不一定能改写为方法引用，因为引用的方法可能并不存在
  
- 方法引用使用一对冒号 ::

  | 类型         | 语法               | 对应的Lambda表达式                   |
  | ------------ | ------------------ | ------------------------------------ |
  | 静态方法引用 | 类名::staticMethod | (args) -> 类名.staticMethod(args)    |
  | 实例方法引用 | inst::instMethod   | (args) -> inst.instMethod(args)      |
  | 对象方法引用 | 类名::instMethod   | (inst,args) -> 类名.instMethod(args) |
  | 构造方法引用 | 类名::new          | (args) -> new 类名(args)             |

  Lambda参数列表中的第一个参数是实例方法的参数调用者，而第二个参数是实例方法的参数时，可以使用对象方法引用。
  
  ```java
  public class Hero {
  
      private String name;
  
      private String ultimateSkill;
  
      public Hero(String name) {
          this.name = name;
      }
  
      public String getName() {
          return name;
      }
  
      public static void showSkill(String s) {
          System.out.println(s);
      }
  
      public void judge(String s){
          if (this.getName().equals("kaiSa") && s.equals("Ash")){
              System.out.println(this.getName() + " win");
          }else {
              System.out.println(s + " win");
          }
      }
  }
  ```
  
  ```java
  public interface HeroFactory<P extends Hero> {
  
      P create(String name, String ultimateSkill);
  
  }
  ```
  
  
  
  ```java
   public class HeroTest {
      public static void main(String[] args) {
          //构造方法引用
          HeroFactory<Hero> heroHeroFactory = Hero::new;
          Hero hero = heroHeroFactory.create("kaiSa", "猎手本能");
          System.out.println(hero.toString());
          //lambda
          Supplier<Hero> hero1 = ()-> new Hero("Ash");
          System.out.println(hero1.get().toString());
          //构造方法引用
          Supplier<Hero> hero2 = Hero::new;
          System.out.println(hero2.get().toString());
          //静态方法引用
          Consumer<String> skill = Hero::showSkill;
          skill.accept("猎手本能");
          //实例方法引用
          Supplier<String> name = hero1.get()::getName;;
          System.out.println(name.get());
          //对象方法引用
          BiConsumer<Hero,String> judge = Hero::judge;
          judge.accept(hero,"Ash");
  
      }
  }
  ```

# 函数式接口（难点）

- 接口中有且只能有一个抽象方法

- 可以有多个静态方法和默认方法

- 使用 `@FunctionalInterface` 标记，编译器如果发现你标注了这个注解的接口有多于一个抽象方法的时候会报错的，也可以不指定` @FunctionalInterface` 

- JDK 1.8 新增加的函数接口：  ``java.util.function``

  | 接口                    | 描述                                                         |
  | ----------------------- | ------------------------------------------------------------ |
  | BiConsumer<T,U>         | 代表了一个接受两个输入参数的操作，并且不返回任何结果         |
  | BiFunction<T,U,R>       | 代表了一个接受两个输入参数的方法，并且返回一个结果           |
  | BinaryOperator<T>       | 代表了一个作用于于两个同类型操作符的操作，并且返回了操作符同类型的结果 |
  | BiPredicate<T,U>        | 代表了一个两个参数的``boolean``值方法                        |
  | BooleanSupplier         | 代表了``boolean``值结果的提供方                              |
  | Consumer<T>             | 代表了接受一个输入参数并且无返回的操作                       |
  | DoubleBinaryOperator    | 代表了作用于两个``double``值操作符的操作，并且返回了一个``double``值的结果 |
  | DoubleConsumer          | 代表一个接受``double``值参数的操作，并且不返回结果           |
  | DoubleFunction<R>       | 代表接受一个``double``值参数的方法，并且返回结果             |
  | DoublePredicate         | 代表一个拥有``double``值参数的``boolean``值方法              |
  | DoubleSupplier          | 代表一个``double``值结构的提供方                             |
  | DoubleToIntFunction     | 接受一个``double``类型输入，返回一个``int``类型结果          |
  | DoubleToLongFunction    | 接受一个``double``类型输入，返回一个``long``类型结果         |
  | DoubleUnaryOperator     | 接受一个参数同为类型``double``,返回值类型也为``double``      |
  | Function<T,R>           | 接受一个输入参数，返回一个结果                               |
  | IntBinaryOperator       | 接受两个参数同为类型``int``,返回值类型也为``int``            |
  | IntConsumer             | 接受一个``int``类型的输入参数，无返回值                      |
  | IntFunction<R>          | 接受一个``int``类型输入参数，返回一个结果                    |
  | IntPredicate            | 接受一个``int``输入参数，返回一个布尔值的结果                |
  | IntSupplier             | 无参数，返回一个``int``类型结果                              |
  | IntToDoubleFunction     | 接受一个``int``类型输入，返回一个``double``类型结果          |
  | IntToLongFunction       | 接受一个``int``类型输入，返回一个``long``类型结果            |
  | IntUnaryOperator        | 接受一个参数同为类型``int``,返回值类型也为``int``            |
  | LongBinaryOperator      | 接受两个参数同为类型``long``,返回值类型也为``long``          |
  | LongConsumer            | 接受一个``long``类型的输入参数，无返回值                     |
  | LongFunction<R>         | 接受一个``long``类型输入参数，返回一个结果                   |
  | LongPredicate           | R接受一个``long``输入参数，返回一个布尔值类型结果            |
  | LongSupplier            | 无参数，返回一个结果``long``类型的值                         |
  | LongToDoubleFunction    | 接受一个``long``类型输入，返回一个``double``类型结果         |
  | LongToIntFunction       | 接受一个``long``类型输入，返回一个``int``类型结果            |
  | LongUnaryOperator       | 接受一个参数同为类型``long``,返回值类型也为``long            |
  | ObjDoubleConsumer<T>    | 接受一个object类型和一个``double``类型的输入参数，无返回值。 |
  | ObjIntConsumer<T>       | 接受一个object类型和一个``int``类型的输入参数，无返回值      |
  | ObjLongConsumer<T>      | 接受一个object类型和一个``long``类型的输入参数，无返回值     |
  | Predicate<T>            | 接受一个输入参数，返回一个布尔值结果                         |
  | Supplier<T>             | 无参数，返回一个结果                                         |
  | ToDoubleBiFunction<T,U> | 接受两个输入参数，返回一个``double``类型结果                 |
  | ToDoubleFunction<T>     | 接受一个输入参数，返回一个``double``类型结果                 |
  | ToIntBiFunction<T,U>    | 接受两个输入参数，返回一个``int``类型结果                    |
  | ToIntFunction<T>        | 接受一个输入参数，返回一个``int``类型结果                    |
  | ToLongBiFunction<T,U>   | 接受两个输入参数，返回一个``long``类型结果                   |
  | ToLongFunction<T>       | 接受一个输入参数，返回一个``long``类型结果                   |
  | UnaryOperator<T>        | 接受一个参数为类型T,返回值类型也为T                          |

  ```java
  @FunctionalInterface
  public interface Hero {
  
      void showUltimateSkill();
      //void show();  报错，@FunctionalInterface 只允许接口有一个抽象方法
  
  }
  ```

  ```java
  	    showHero(new Hero() {
              @Override
              public void showUltimateSkill() {
                  System.out.println("猎手本能");
              }
          });
          showHero(()->{
              System.out.println("");
          });
  
          List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
          showList(list,new IntFunction<Integer>(){
              @Override
              public Integer apply(int value) {
                  return value - 5;
              }
          });
          showList(list,value -> value - 7);
  
   	//自定义函数接口
      public static void showHero(Hero hero){
          hero.showUltimateSkill();
      }
      //1.8新增接口
      public static void showList(List<Integer> list, IntFunction<Integer> intFunction) {
          list.forEach(a -> {if (intFunction.apply(a)>0) {
              System.out.println(a);
              }
          });
      }
  
  ```

  

  # Stream(重要)

  ## Stream介绍

  - Java 8 API添加了一个新的抽象称为流Stream，可以让你以一种声明的方式处理数据

  - Stream使用一种类似用SQL语句从数据库查询数据的直观方式来提供一种对Java集合运算和表达的高阶抽象

  - 这种风格将要处理的元素集合看作一种流，流在管道中传输，并且可以在管道的节点上进行处理，比如筛选，排序，聚合等

  - 元素流在管道中经过中间操作的处理，最后由最终操作得到前面处理的结果

  - Stream是一个来自数据源的元素队列并支持聚合操作

    - 元素：是特定类型的对象，形成一个队列。Java中的Stream并不会存储元素，而是按需计算。

    - 数据源 ：流的来源。可以是集合，数组，I/O channel，产生器generator等。

    - 聚合操作： 类似SQL语句一样的操作，比如filter, map, reduce, find,match, sorted等。

    - 和以前的Collection操作不同，Stream操作还有两个基础的特征：

      - Pipelining:：中间操作都会返回流对象本身。这样多个操作可以串联成一个管道，如同流式风格（fluent style）。这样做可以对操作进行优化，比如延迟执行(laziness)和短路( short-circuiting)。

      - 内部迭代：以前对集合遍历都是通过Iterator或者For-Each的方式,显式的在集合外部进行迭代，这叫做外部迭代。Stream提供了内部迭代的方式，通过访问者模式(Visitor)实现。

  ## 使用

  - java8结合接口中有两个方法生成流：stream()创建串行流，parallelStream()创建并行流。

    | 方法                  | 介绍                                                         |
    | --------------------- | ------------------------------------------------------------ |
    | forEach               | Stream 提供了新的方法 forEach' 来迭代流中的每个数据          |
    | map                   | map 方法用于映射每个元素到对应的结果                         |
    | filter                | filter 方法用于通过设置条件过滤出元素                        |
    | limit                 | limit 方法用于获取指定数量的流                               |
    | sorted                | sorted 方法用于对流进行排序                                  |
    | Collectors            | Collectors 类实现了很多归约操作，例如将流转换成集合和聚合元素 |
    | 统计summaryStatistics | 主要用于int、double、long等基本类型上，getMax()，getMin()，getSum()，getAverage() |

    ```java
     public static void main(String[] args) {
            List<String> strings = Arrays.asList("abd","abc","ef","fgh","a","");
            strings.stream()
                    .filter(s -> !s.isEmpty())
                    .limit(8)
                    .forEach(System.out::println);
    
    
            List<Integer> integers = Arrays.asList(1,2,3,3,4,5,6);
            integers.stream()
                    .map(Math::sqrt)
                    .sorted()
                    .distinct()
                    .forEach(System.out::println);
            
            IntSummaryStatistics statistics = 			integers.stream().mapToInt(Integer::intValue).summaryStatistics();
            System.out.println(statistics.getAverage());
    
        }
    ```

    # Optional 类

    - Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象

    - Optional 类的引入很好的解决空指针异常。

      ```java
      public static void main(String[] args) {
          Integer integer = null;
          Integer integer1 = new Integer(10);
          System.out.println(sum(integer1, Optional.ofNullable(integer)));
          System.out.println(sum(integer, Optional.of(integer1)));
      }
      public static int sum(Integer a, Optional<Integer> b){
          Integer defaultB = b.orElse(0);
          return a + defaultB;
      }
      ```

    #  Date/Time API

    ## 设计原则、

    - 不变性：新的日期/时间API中，所有的类都是不可变的，这种设计有利于并发编程。

    - 关注点分离：新的API将人可读的日期时间和机器时间（unix timestamp）明确分离，它为日期（Date）、时间（Time）、日期时间（DateTime）、时间戳（unix timestamp）以及时区定义了不同的类。

    - 清晰：在所有的类中，方法都被明确定义用以完成相同的行为。

    - 实用操作：所有新的日期/时间API类都实现了一系列方法用以完成通用的任务，如：加、减、格式化、解析、从日期/时间中提取单独部分等操作。

    ##  使用

    - 时间大致可以分为三个部分：日期、时间、时区，其中日期又细分为年、月、日；时间又细分为时、分、秒

    - 一般机器时间用从 1970-01-01T00:00，时间戳指的是秒数，而不是毫秒数。

      | 类                         | 用法                                                         |
    | -------------------------- | ------------------------------------------------------------ |
      | Clock 时钟                 | Clock类提供了访问当前日期和时间的方法，Clock是时区敏感的     |
      | Timezones 时区             | 在新API中时区使用ZoneId来表示。时区可以很方便的使用静态方法of来获取到 |
      | Instant                    | 表示时间线上的一个点，参考点是标准的Java纪元(epoch)，即1970-01-01T00：00：00Z |
      | LocalTime 本地时间         | LocalTime 定义了一个没有时区信息的时间，对象值是不可变的     |
      | LocalDate 本地日期         | LocalDate 表示了一个确切的日期，对象值是不可变的             |
      | LocalDateTime 本地日期时间 | LocalDateTime 同时表示了时间和日期，对象值是不可变的         |
      | ZonedDateTime              | 日期+时间+时区值对象                                         |
      | DateTimeFormatter          | 用于日期时间的格式化                                         |
      | Period                     | 用于计算日期间隔                                             |
      | Duration                   | 用于计算时间间隔                                             |
      
      ```java
           public static void main(String[] args) {
              Clock clock = Clock.systemDefaultZone();
              long millis = clock.millis();
              System.out.println(millis);
              Instant instant = clock.instant();
              Date legacyDate = Date.from(instant);
              System.out.println(legacyDate);
              System.out.println(ZoneId.getAvailableZoneIds());
      
      
              ZoneId zone1 = ZoneId.of("Europe/Berlin");
              ZoneId zone2 = ZoneId.of("Brazil/East");
              System.out.println(zone1.getRules());
              System.out.println(zone2.getRules());
      
              LocalTime now1 = LocalTime.now(zone1);
              LocalTime now2 = LocalTime.now(zone2);
              System.out.println(now1);
              System.out.println(now1.plusHours(5));
      
              System.out.println(now1.isBefore(now2));
      
              long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
              long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
      
              System.out.println(hoursBetween);
              System.out.println(minutesBetween);
      
              LocalTime late = LocalTime.of(23, 59, 59);
              System.out.println(late);
  
              LocalDate date1 = LocalDate.now(ZoneId.systemDefault());
          System.out.println(date1);
              System.out.println(date1.atStartOfDay());
          System.out.println(date1.getDayOfWeek());
              System.out.println(date1.plusDays(3));
      }
      ```
  
      
    
     # CompletableFuture（不重要）
    
    - 传统的Future虽然可以实现获取异步执行结果的需求，但是它没有提供通知的机制，我们无法得知Future什么时候完成。
  
- CompletableFuture能够将回调放到与任务不同的线程中执行，也能将回调作为继续执行的同步函数，在与任务相同的线程中执行。它避免了传统回调最大的问题，那就是能够将控制流分离到不同的事件处理器中。
  
- CompletableFuture弥补了Future模式的缺点。在异步的任务完成后，需要用其结果继续操作时，无需等待。可以直接通过thenAccept、thenApply、thenCompose等方式将前面异步处理的结果交给另外一个异步事件处理线程来处理
  
      | 方法名                                               | 描述                                                         |
      | ---------------------------------------------------- | ------------------------------------------------------------ |
      | runAsync(Runnable runnable)                          | 使用ForkJoinPool.commonPool()作为它的线程池执行异步代码。    |
      | runAsync(Runnable runnable, Executor executor)       | 使用指定的thread pool执行异步代码。                          |
      | supplyAsync(Supplier<U> supplier)                    | 使用ForkJoinPool.commonPool()作为它的线程池执行异步代码，异步操作有返回值 |
      | supplyAsync(Supplier<U> supplier, Executor executor) | 使用指定的thread pool执行异步代码，异步操作有返回值          |
  
    - 线程对象方法
    
      | 方法名                              | 描述                                                         |
    | ----------------------------------- | ------------------------------------------------------------ |
      | get()                               | 获取线程的执行结果                                           |
    | complete(T t)                       | 立即执行线程，并返回结果，只能调用一次且线程执行结束调用也无效 |
      | completeExceptionally(Throwable ex) | 异步执行不正常的结束                                         |
      
       ```java
      CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                  System.out.println("Hello");
              });
              CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "KaiSa");
      
              try {
                  future1.complete("KaiSa");
                  future1.completeExceptionally(new NullPointerException());
                  System.out.println(future1.get());
                  System.out.println(future.get());
                  System.out.println(future1.get());
              } catch (InterruptedException e) {
                  e.printStackTrace();
              } catch (ExecutionException e) {
                  e.printStackTrace();
              }
       ```
      
      ## 扩展：Executors线程池类
      
      
  
      | 方法                             | 描述                                               |
  | -------------------------------- | -------------------------------------------------- |
      | newCacheThreadPool               | 必要时创建线程，处于空闲状态线程将被保留60秒       |
  | newFixedThreadPool               | 拥有固定数量的线程，并且不会自动销毁空闲状态的线程 |
      | newSingleThreadExecutor          | 该线程池仅有一个线程，会顺序执行任务队列           |
  | newScheduledThreadPool           | 用于预约执行任务的固定线程池                       |
      | newSingleThreadScheduledExecutor | 用于预约执行任务的单线程池                         |
  
    # Base64(不重要)

    - Base64是网络上最常见的用于传输8Bit字节码的编码方式之一，Base64就是一种基于64个可打印字符来表示二进制数据的方法\

    - Base64一般用于在HTTP协议下传输二进制数据，由于HTTP协议是文本协议，所以在HTTP协议下传输二进制数据需要将二进制数据转换为字符数据

    - 网络传输只能传输可打印字符。在ASCII码中规定，0~31、127这33个字符属于控制字符，32~126这95个字符属于可打印字符，网络传输只能传输这95个字符，不在这个范围内的字符使用Base64。(有点绕)
    
    - Base64，就是使用64个可打印字符来表示二进制数据的方法
    
    - Base64工具类提供了一套静态方法获取下面三种BASE64编解码器：
    
      - 基本：输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/。
      
      -  URL：输出映射到一组字符A-Za-z0-9+_，输出是URL和文件。
      
      - MIME：输出隐射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割
      
      ```java
              String encode = Base64.getEncoder().encodeToString("卡	  莎".getBytes(StandardCharsets.UTF_8));
              System.out.println(encode);
              byte[] decode = Base64.getDecoder().decode(encode);
              System.out.println(new String(decode, StandardCharsets.UTF_8));
      ```
      
      
  
  ​			
  
  

​    

​    

​    
