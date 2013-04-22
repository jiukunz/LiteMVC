Model-View-Controller是面向对象的圣杯。在桌面时代MVC充分展示了面向对象技法的优雅，而第一面向对象语言Smalltalk（my favorite）也完全地构件在MVC之上。在GoF的Design Pattern里，MVC也是作为架构模式出现在第一章里。其他模式则是作为对MVC的支撑来讨论的。但是，what get you here won't bring you there。随着Web的兴起，以事件监听作为基础的MVC无法完全适应Web时代的要求（*见注1），于是MVC Model2 应运而生了，第一代表作就是Struts 1（上周终于宣布End of life了），之后Struts 1以其特有的丑陋，开启了java web framework时代（完全就是Java世界的Gol.D.Roger），03－06年几乎每周都有一个新的mvc web framework诞生，直到rails得到认可为止。其中有WebWork，Struts 2，Spring MVC等等。所以我们这周要挑战的就是MVC Model 2 WebFramework！！

功能要求

1. The implementation must support model driven page render with at least one choice of template engines(JSP, FreeMarker，StringTemplate，Velocity，Mustache)
2. The implementation must support form submit, including nested structure.
3. The implementation must support service injection for controller, and must use your di container you finished last round(unless the loser who choose to use Guice)
4. The implementation must support run in a embedded web container(Grizzly, Jetty or Tomcat)
5. The support of web.xml is not mandatory

实现要求

1. Functioning properly, and robust.
2. No other library apart from guava, xunit, testing tools, and the template engine you chose.