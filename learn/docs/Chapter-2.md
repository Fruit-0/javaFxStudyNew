# 第 2 章 深入 JavaFX 程序设计

> **在本章节**
>
> + 导入创建 JavaFX 程序所需的类
> + 创建一个继承 JavaFX `Application` 类的类
> + 使用 `Button`、`BorderPane` 和 `Scene` 等类创建用户界面
> + 创建用户单击按钮时调用的事件处理程序
> + 验证 Click Me 程序的增强版本

在第 1 章中，我介绍了名为 Click Me 的简单的 JavaFX 程序，并简单描述了它是如何工作的。本章中，我会把这个程序放在显微镜下，进行详细研究。阅读完本章，你将了解 Click Me 程序的每一行是如何工作的，以及为什么需要这样做。然后， 你就可以开始研究 JavaFX 编程更细微的技术了。

## 再看 Click Me 程序

图 2-1 展示了运行中的 Click Me 程序。若你所见，上面显示了一个包含 `Click me please!` 几个字的简单按钮。图中没有展示出来的是，当用户单击按钮时，它上面的文字将变成 `I’ve Been Clicked!`。

> 图 2-1：运行中的 Click Me 程序。

![Figure 2-1](assets/Figure-2-1.png)

尽管这个程序很简单，但它展示了大多数你需要掌握的用于编写 JavaFX 程序的基本技术：

> ✓ 显示一个用户界面，上面包含一个标准控件 —— 这个例子中是按钮。
>
> ✓ 对用户的输入进行响应，出现在用户点击按钮时。
>
> ✓ 更新显示以确认用户的操作。

许多 JavaFX 程序都由这个简单主题变化而来：创建用户界面，响应用户的输入，然后更新显示以反映用户的输入。更为实际的 JavaFX 程序显示的用户界面必定不仅仅只有一个按钮。为响应用户输入而执行的处理还可能包括其他步骤，比如在数据库中查找信息或是执行计算。毫无疑问，显示的内容也将以更复杂的方式进行更新，而不仅仅是更改按钮上显示的文本。不过，在大多数真实的 JavaFX 程序中都可以找到这些基本要素的变体。

清单 2-1 显示了 Click Me 程序的完整代码。本章的剩余部分将对这个程序的每一行进行详细解释。

**清单 2-1：Click Me 程序**

```java
import javafx.application.*; 
import javafx.stage.*; 
import javafx.scene.*; 
import javafx.scene.layout.*; 
import javafx.scene.control.*;

public class ClickMe extends Application {

  public static void main(String[] args) { 
    launch(args); 
  }

  Button btn;

  @Override 
  public void start(Stage primaryStage) {
    // Create the button 
    btn = new Button(); 
    btn.setText("Click me please!"); 
    btn.setOnAction(e -> buttonClick());

    // Add the button to a layout pane 
    BorderPane pane = new BorderPane(); 
    pane.setCenter(btn);

    // Add the layout pane to a scene 
    Scene scene = new Scene(pane, 300, 250);

    // Finalize and show the stage 
    primaryStage.setScene(scene); 
    primaryStage.setTitle("The Click Me App"); 
    primaryStage.show();
  }

  public void buttonClick() {
    if (btn.getText() == "Click me please!") {
      btn.setText("You clicked me!"); 
    } else {
      btn.setText("Click me please!"); 
    }
  }
}
```

## 导入 JavaFX 包

JavaFX 程序与其他 Java 程序一样，以一系列 `import` 语句开头，这些语句引入了程序将会使用的各种 JavaFX 包。Click Me 程序包含了以下五个 `import` 语句：

```java
import javafx.application.*; 
import javafx.stage.*; 
import javafx.scene.*; 
import javafx.scene.layout.*; 
import javafx.scene.control.*;
```

如你所见，所有的 JavaFX 包都以 `javafx` 开头。Click Me 程序使用的类来自五个不同的 JavaFX 包：

> **✓ `javafx.application`：** 这个包中定义了所有 JavaFX 应用程序都依赖的核心类：`Application`。稍后你会在本章“继承 Application 类”一节中了解到更多关于 `Application` 类的信息。
>
> **✓ `javafx.stage`：** 这个包中最重要的类是 `Stage`，它定义了所有用户界面对象的顶级容器。`Stage` 是 JavaFX 应用程序的最高级别的窗口，应用程序所有的用户界面元素都显示在其中。
>
> **✓ `javafx.scene`：** 这个包中最重要的类是 `Scene`，它是一个容纳着程序显示的所有用户界面元素的容器。
>
> **✓ `javafx.scene.layout`：** 这个包中定义了一种特殊类型的用户界面元素，布局管理器。它们的工作是确定每个控件在用户界面中的显示位置。
>
> **✓ `javafx.scene.control`：** 这个包中定义了各个用户界面控件对应的类，如按钮、文本框和标签。Click Me 程序只使用了这个包中的一个类：`Button`，它表示一个用户可以点击的按钮。

## 继承 Application 类

JavaFX 应用程序是一个继承了 `javafx.application. Application` 的 Java 类。因此，Click Me 应用程序的主类声明是这样的：

```java
public class ClickMe extends Application
```

在这里，Click Me 应用程序由继承了 `Application` 的 `ClickMe` 的类定义。

<img src="assets/technical-stuff.png" width="50"/>因为在 Click Me 程序的第 1 行中导入了整个 `javafx.application` 包，所以 `Application` 类不需要使用全限定名。如果省略了 `javafx.application` 包的 `import` 语句，`ClickMe` 类的声明需要变成这样：

```java
public class ClickMe extends javafx.application.Application
```

`Application` 类负责管理 JavaFX 应用程序的生命周期。生命周期包括以下步骤：

1. 创建 `Application` 类的一个实例。

2. 调用 `init` 方法。

   `init` 方法的默认实现不做任何事情，可以通过覆盖 `init` 方法，在应用程序的用户界面显示之前执行你想要的任何处理。

3. 调用 `start` 方法。

   `start` 方法是一个抽象方法，这意味着 `Application` 类没有提供默认实现。因此，你必须提供自己的 `start` 方法版本。`start` 方法负责构建和显示用户界面。（更多信息，稍后请阅读“重写 start 方法”一节）

4. 等待应用程序结束，这通常发生在用户通过关闭主应用程序窗口或选择程序的退出命令来指示程序结束时。

   在此期间，应用程序并不是真正空闲的。相反，它忙于响应用户事件，例如单击按钮或从下拉列表中选择。

5. 调用 `stop` 方法。

   与`init` 方法一样，`stop` 方法的默认实现没有任何操作。你可以通过覆盖它，以实现在程序结束时执行一些必要的处理，例如关闭数据库或是保存文件。

## 启动应用程序

你应该知道，`main` 方法是 Java 程序的标准入口。以下就是 Click Me 程序的 `main` 方法：

```java
public static void main(String[] args) { 
  launch(args); 
}
```

如你所见，`main` 方法只包含一条语句，即对 `Application` 类的 `launch` 方法的调用。

`launch` 方法实际上是启动 `JavaFX` 应用程序的方法。 `launch` 方法是静态方法，所以可以在 `main` 方法的静态上下文中被调用。它创建了一个 `Application` 类实例，然后启动 JavaFX 生命周期，调用 `init` 和 `start` 方法，等待应用程序完成，再调用 `stop` 方法。

`starty` 方法直到 JavaFX 应用程序结束时才会返回。假设你为 Click Me 程序编写的 `main` 方法是这样的：

```java
public static void main(String[] args) {
  System.out.println("Launching JavaFX");
  launch(args);
  System.out.println("Finished"); 
}
```

当 JavaFX 应用程序窗口打开时，你会看到控制台窗口显示 `Launching JavaFX`。当你关闭 JavaFX 应用程序窗口，你会看到控制台显示 `Finished`。

## 重写 start 方法

每个 JavaFX 应用程序都必须包含一个 `start` 方法。在 `start` 方法中，你可以编写代码创建与用户进行交互的界面元素。例如，清单 2-1 中的 `start` 方法包含的代码展示了一个带有 `Click me please!` 字样的按钮。

当 JavaFX 应用程序启动时，JavaFX 框架会在 `Application` 类初始化之后调用 `start` 方法。

Click Me 程序的 `start` 方法如下所示：

```java
@Override public void start(Stage primaryStage) {
  // Create the button 
  btn = new Button(); 
  btn.setText("Click me please!"); 
  btn.setOnAction(e -> buttonClick());

  // Add the button to a layout pane 
  BorderPane pane = new BorderPane(); 
  pane.setCenter(btn);

  // Add the layout pane to a scene 
  Scene scene = new Scene(pane, 300, 250);

  // Finalize and show the stage 
  primaryStage.setScene(scene); 
  primaryStage.setTitle("The Click Me App"); 
  primaryStage.show();
}
```

为了创建 Click Me 程序的用户界面，`start` 方法执行了以下四个基本步骤：

1. 创建一个名为 `btn` 的按钮控件，将其文本设置为 `Click me please!`，并指定用户单击按钮时调用名为 `buttonClick` 的方法。

   有关此代码的详细说明，请参阅本章后面的“创建按钮”和“处理操作事件”部分。

2. 创建一个名为 `pane` 的布局面板，并将按钮添加到其中。

   更多细节，请参阅本章后面的“创建布局面板”部分。

3. 创建一个名为 `scene` 的场景，并将布局面板添加到其中。

   更多细节，请参阅本章后面的“场景搭建”部分。

4. 通过设置场景、设置舞台标题、展示舞台，最终完成舞台（stage）搭建。

   请参阅本章后面的“舞台设置”部分了解更多细节。

你将在本章后续部分找到这些代码块的相关细节。但是在继续之前，我想额外指出一些有关 start 方法的重要细节：

> ✓ 在 `Application` 类中，`start` 方法被定义为一个抽象方法，所以当你在 JavaFX 程序中包含 `start` 方法时，实际上覆盖了抽象的 start 方法。
>
> <img src="assets/tip.png" width="50"/>尽管不是必需的，但最好还是用 `@override` 注解明确声明你重写了 `start` 方法。如果省略了这个注解，当你拼错了方法名（例如，写成了 `Start` 而不是 `start`），或者列出的参数不正确，Java 会认为你是在定义一个新方法，而不是覆盖 start 方法。
>
> ✓ 与 `main` 方法不同，`start` 方法不是静态方法。当你从静态 `main` 方法调用 `launch` 方法时，`launch` 方法将创建 `Application` 类的一个实例，然后调用 `start` 方法。
>
> ✓ `start` 方法接受一个参数：`Stage` 对象，应用程序的用户界面将在其上进行显示。当应用程序调用你的 `start` 方法时，会通过 `primaryStage` 参数传递主舞台（primary stage）。因此，你可以在稍后的 `start` 方法中使用 `primaryStage` 参数来引用应用程序的舞台。

## 创建按钮

Click Me 程序显示的按钮是使用名为 `Button` 的类创建的。该类是可用于创建用户界面控件的众多类之一。 `Button` 和其他大多数控件类可以在 `javafx.scene.control`  包中找到。

要创建一个按钮，只需定义一个 `Button` 类型的变量，然后调用它的构造函数：

```java
Button btn; 
btn = new Button();
```

在清单 2-1 中的代码中，`btn` 在 `start` 方法之外声明为类变量，而 `Button` 对象实际上是在 `start` 方法内创建的。控件通常声明为类变量，以便你可以在类中定义的任何方法中访问它们。正如你会在下一节（“处理操作事件”）了解到的，当用户单击按钮时，将调用一个名为 `buttonClicked` 的单独方法。通过将 `btn` 定义为类变量，`start` 方法和 `buttonClicked` 方法都可以对它进行访问。

要设置按钮显示的文本，可以调用 `setText` 方法，并将要显示的文本作为字符串进行传递：

```java
btn.setText("Click me please!");
```

以下是有关按钮的一些趣闻：

> ✓ `Button` 构造函数允许你把要在按钮上显示的文本作为参数传递，如下：
>
> ```java
>Btn = new Button("Click me please!");
> ```
>
> 如果以这种方式设置按钮的文本，就不需要再调用 `setTitle` 方法。
> 
> <img src="assets/technical-stuff.png" width="50"/> ✓ `Button` 类是父类 `javafx.scene.control.Control` 派生的众多类之一。该类还派生了许多其他类，包括 `Label`，`TextField`，`ComboBox`，`CheckBox` 和 `RadioButton`。
>
> <img src="assets/technical-stuff.png" width="50"/>✓ `Control` 类是从更高级别的父类 `javafx.scene.Node` 派生的几种类之一。`Node` 是所有可以在场景中显示的用户界面元素的基类。控件是一种特定类型的节点，除此之外，还有其他类型的节点。换句话说，所有控件都是节点，但并非所有节点都是控件。你可以在本书后面的内容中详细了解其他几种类型的节点。

## 处理活动事件

用户单击按钮时，会触发一个活动事件。你的程序可以通过提供一个事件处理程序（指的是一些在事件发生时执行的代码）来响应事件。Click Me 程序便是通过给按钮设置代码为更改按钮上显示文本的处理程序来工作。

正如你从第 3 章读到的，JavaFX 中有几种处理事件的方法。现在，我简要介绍最简单的方法，它只需要你指定事件发生时调用的方法，并提供实现该方法的代码。

要指定用户单击按钮时调用的方法，可以调用 `Button` 类的 `setOnAction` 方法。如清单 2-1 所示：

```java
btn.setOnAction(e -> buttonClick());
```

<img src="assets/tip.png" width="50"/>这里使用的语法可能看起来有点陌生，因为它使用了 Java 8 的一个新特性 —— Lambda 表达式。在示例中，这个新语法包含三个元素：

> ✓ 参数 `e` 表示一个 `ActionEvent` 类型的对象，程序可以使用它来获取事件的详细信息。
>
> Click Me 程序忽略了这个参数，你也可以忽略它，至少现在是这样。
>
> ✓ 箭头操作符（->）是 Java 8 中引入的用于 Lambda 表达式的新运算符。
>
> ✓ `buttonClick()` 简单地调用名为 `buttonClick` 的方法。

我将在第 3 章讨论 Lambda 表达式。

在将 `buttonClick` 作为用户单击按钮时调用的方法之后，下一步是编写 `buttonClick` 方法的代码。你可以在清单 2-1 的底部找到它：

```java
public void buttonClick() {
  if (btn.getText() == "Click me please!") {
    btn.setText("You clicked me!"); 
  } else {
    btn.setText("Click me please!"); 
  }
}
```

该方法使用 `if` 语句交替地将按钮显示的文本更改为 `You clicked me!` 或 `Click me please!`。换句话说，如果按钮上的文本是 `Click me please!`，当用户单击按钮时，`buttonClicked` 方法会把它改为 `You clicked me!`。反之，`if` 语句会将按钮的文本更改回 `Click me please!`。

`buttonClicked` 使用 `Button` 类的两个方法来完成它的工作：

> **✓ getText：** 以字符串形式返回按钮上显示的文本
>
> **✓ setText：** 设置按钮显示的文本

<img src="assets/cross-reference.png" width="50"/>有关处理事件的更多信息，请参阅第 3 章。

## 创建布局面板

光有按钮没什么作用，你必须将它显示在屏幕上，用户才能对它进行点击。实际的 JavaFX 程序都有不止一个控件。当你将第二个控件添加到用户界面时，你需要一种方法来指定控件之间的相对位置。比如，如果你的应用程序有两个按钮，你是希望它们一个在另一个之上垂直堆放，还是并排摆放?

此时，布局面板就能派上用场。布局面板是容器类的一种，你可以往其中添加一个或多个用户界面元素。布局面板会根据这些元素的相对关系决定如何准确地显示它们。

要使用布局面板，首先要创建它的实例。然后，将一个或多个控件添加到面板中。在这一步，你可以指定面板显示时控件是如何摆放的细节。将所有的控件添加到面板中并进行排列后，再将面板添加到场景中。

JavaFX 总共提供了 8 种不同类型的布局面板，都是由 `javafx.scene.layout` 包中类定义的。Click Me 程序使用了一种称为边界面板的布局类型，它将面板中的内容摆放在五个常规区域：顶部、左侧、右侧、底部和中心。`BorderPane` 类非常适合这样的布局：在顶部有菜单和工具栏，在底部有状态栏，可选任务面板或工具栏在左边或右边，主工作区域在屏幕中央。

Click Me 程序中创建边界面板的代码是：

```java
BorderPane pane = new BorderPane(); 
pane.setCenter(btn);
```

在这里，声明了一个名为 `pane` 的 `BorderPane` 类型的变量，并调用 `BorderPane` 构造函数创建了一个新的 `BorderPane` 对象。然后，使用 `setCenter` 方法将按钮（btn）显示在面板的中心区域。

以下是有关布局面板一些其他有趣的细节：

> ✓ 布局面板会根据布局中包含的元素的大小以及显示布局面板的空间的大小，自动调整其所包含元素的位置。
>
> <img src="assets/technical-stuff.png" width="50"/> ✓ 前面说过，控件是一种节点，你将在本书后面的内容中了解其他类型的节点。是的，如你刚刚读到的，布局面板也是一种节点。
>
> ✓ 边界面板的每个区域都可以包含一个节点。因为布局面板本身是节点的一种，所以边界窗格的每个区域都可以包含另一个布局面板。例如，假设你想在边界面板的中心区域显示三个控件。为此，你需要创建第二个布局面板并将三个控件添加到其中。然后，将第二个布局面板设置到第一个布局面板的中心区域。
>
> <img src="assets/cross-reference.png" width="50"/>✓ 你可以在第 5 章阅读到更多关于 `BorderPane` 类和其他常用的布局面板相关的内容。在第 13 章中，你还将了解到一些不常用的布局面板。

## 场景搭建

在创建了包含要显示的控件的布局面板之后，下一步是创建一个用于显示布局面板的场景（scene）。可以用一行代码声明 `Scene` 类型的变量并调用它的构造函数来完成此操作。以下是我在 Click Me 程序中的做法：

```java
Scene scene = new Scene(pane, 300, 250);
```

`Scene` 类的构造函数接受三个参数：

> ✓ 一个 `node` 对象，代表场景中显示的根节点（root node）。
>
> 一个场景只能有一个根节点，因此根节点通常是一个布局面板，该面板依次包含要显示的其他控件。在 Click Me 程序中，根节点是包含着按钮的边界布局面板。
>
> ✓ 场景宽度（以像素为单位）。
>
> ✓ 场景高度（以像素为单位）。

注意：如果省略宽度和高度，将根据根节点中包含的元素的大小自动调整场景的大小。

<img src="assets/cross-reference.png" width="50"/>你可以在第 4 章中找到 `Scene` 类的一些其他功能。

## 舞台设置

如果场景（scene）表示应用程序所有要显示的节点（控件和布局面板），那么舞台（stage ）就表示显示场景的窗口。当 JavaFX 框架调用应用程序的 `start` 方法时，会通过 `primaryStage` 参数传递一个 `Stage` 类的实例，其表示应用程序的主舞台 —— 即应用程序主窗口的舞台。

创建了场景之后，你就可以完成主舞台，以便显示场景。为此，至少要做两件事:

> ✓ 调用主舞台的 `setScene` 方法来设置要显示的场景。
>
> ✓ 调用主舞台的 `show` 方法来显示场景。
>
> 调用 `show` 方法后，应用程序的窗口对用户可见，用户可以开始与其中的控件进行交互。

习惯上也会将标题设置在应用程序标题栏上进行显示，你可以通过调用主舞台的 `setTitle` 方法来实现。 Click Me 应用程序 `start` 方法的最后三行便实现了以上功能：

```java
primaryStage.setScene(scene); 
primaryStage.setTitle("The Click Me App"); 
primaryStage.show();
```

当最后一行的 `show` 方法被调用时，舞台便显示出来 —— 换句话说，如图 2-1 所示的窗口将显示在屏幕上。

<img src="assets/cross-reference.png" width="50"/>你可以在第 4 章了解到 `Stage` 类的其他功能。

## 检查 Click Counter 程序

我已经对 Click Me 程序的每一行进行了详细解释，现在来看一下 Click Me 程序一个略微增强的版本 —— Click Counter。在第 1 章的清单 1-1 所示的 Click Me 程序中，当用户单击按钮时，按钮上显示的文本会发生变化。而在 Click Counter 程序中，用户单击按钮的次数会显示在一种称为标签（label）的控件中。

图 2-2 展示了运行中的 Click Counter 程序。顶部的窗口展示了首次启动 Click Counter 程序时的样子。如你所见，标签中显示的文本是 `You have not clicked the button.`。第二个窗口显示了第一次单击按钮后程序的样子。在这里，标签显示 `You have clicked once.`。第二次单击按钮时，标签再次更改，如第三个窗口所示。在这里，标签显示 `You have clicked 2 times.`。在此之后，每次点击按钮时，标签上显示的数字都会更新，以指示按钮被点击了多少次。

> 图 2-2：运行中的 Click Counter 程序。

![Figure 2-2](assets/Figure-2-2.png)

清单 2-2 展示了 Click Counter 程序的源代码，紧跟着描述了它是如何工作的关键点:

**Listing 2-2: The Click Counter Program**

```java
import javafx.application.*;                                                      // →1
import javafx.stage.*; 
import javafx.scene.*;
import javafx.scene.layout.*; 
import javafx.scene.control.*;

public class ClickCounter extends Application                                     // →7
{ 
  public static void main(String[] args)                                          // →9
  { 
    launch(args);                                                                 // →11
  }

  Button btn;                                                                     // →14
  Label lbl;                                                                      // →15
  int iClickCount = 0;                                                            // →16

  @Override public void start(Stage primaryStage)                                 // →18
  { 
    // Create the button 
    btn = new Button();                                                           // →21
    btn.setText("Click me please!");                                              // →22
    btn.setOnAction(e -> buttonClick());                                          // →23

    // Create the Label 
    lbl = new Label();                                                            // →26
    lbl.setText("You have not clicked the button.");                              // →27

    // Add the label and the button to a layout pane 
    BorderPane pane = new BorderPane();                                           // →30
    pane.setTop(lbl);                                                             // →31
    pane.setCenter(btn);                                                          // →32

    // Add the layout pane to a scene 
    Scene scene = new Scene(pane, 250, 150);                                      // →35

    // Add the scene to the stage, set the title 
    // and show the stage 
    primaryStage.setScene(scene);                                                 // →39
    primaryStage.setTitle("Click Counter");                                       // →40
    primaryStage.show();                                                          // →41
  }

  public void buttonClick()                                                       // →44
  { 
    iClickCount++;                                                                // →46
    if (iClickCount == 1)                                                         // →47
    {
      lbl.setText("You have clicked once.");                                      // →49
    }  
    else 
    {
      lbl.setText("You have clicked " + iClickCount + " times." );                // →53
    }
  }
}
```

以下段落说明了 Click Me 程序的要点：

➝ 1：`import` 语句引用了 Click Me 程序将要用到的 `javafx` 包。

➝ 7：`ClickMe` 类继承了 `javafx.application.Application`，说明 `ClickMe` 类是一个 JavaFX 应用程序。

➝ 9：与其他 Java 程序一样，`main` 方法是所有 JavaFX 程序的主入口。

➝ 11：`main` 方法调用 `Application` 类定义的 `launch` 方法。`launch ` 方法先创建 `ClickMe` 类的实例，再调用 `start` 方法。

➝ 14：`javafx.scene.control.Button` 类型的一个变量 `btn` 被声明为一个类变量。代表 JavaFX 控件的变量通常被定义为类变量，以便可以在该类的任何方法中进行访问。

➝ 15：一个名为 `lbl`，类型为 `javafx.scene.control.Label` 的类变量，可以在类的任何方法中访问它。

➝ 16：一个名为 `iClickCount` 的类变量，用于跟踪用户单击按钮的次数。

➝ 18：在 `start` 方法的声明上使用了 `@override` 注解，表示该方法覆盖了 `Application` 类提供的默认 `start` 方法。 `start` 方法接受一个名为 `primaryStage` 的参数，它表示 Click Me 应用程序将在其中显示用户界面的窗口。

➝ 21：`start` 方法首先会创建一个 `Button` 对象，并将其赋值给一个名为 `btn` 的变量。

➝ 22：调用按钮的 `setText` 方法将它显示的文本设置为 `Click me please!`。

➝ 23：调用 `setOnAction` 为按钮创建事件处理程序。在这里，使用 Lambda 表达式在用户单击按钮时调用 `buttonClick ` 方法。

➝ 26：调用 `Label` 类的构造函数创建一个新标签。

➝ 27：调用标签的 `setText` 方法将标签的初始文本值设置为 `You have not clicked the button.`。

➝ 30：调用 `BorderPane` 类的构造函数创建一个边界面板对象，并将名为 `pane` 的变量作为它的引用 。边界面板将用于控制屏幕上显示的控件的布局。

➝ 31：调用边界面板的 `setTop` 方法将标签添加到边界面板的顶部区域。

➝ 32：调用边界面板的 `setCenter` 方法将按钮添加到边界面板的中心区域。

➝ 35：调用 `Scene` 类的构造函数创建一个场景对象，并将第 30 行创建的边界面板传递给它的构造函数，从而将边界面板作为场景的根节点。此外，还将场景的尺寸设置为宽度 300 像素，高度 250 像素。

➝ 39：`primaryStage` 的 `setScene` 方法用于将场景添加到主舞台。

➝ 40：`setTitle` 方法用于设置在主舞台标题栏中显示的文本。

➝ 41：调用 `show` 方法以显示主舞台。当这一行执行后，屏幕上将显示如图 2-1 所示的窗口，用户可以开始与程序进行交互。

➝ 44：每当用户单击按钮时，都会调用 `buttonClick` 方法。

➝ 46：`iClickCount ` 变量会递增，以表示用户已经单击了按钮。

➝ 47：`if` 语句用于确定按钮是被点击了一次还是多次。

➝ 49：如果按钮第一次被点击，将标签文本被设置为 `You have clicked once.`。

➝ 53：否则，将标签文本设置为表示按钮被点击了多少次的字符串。

就是这样。如果你理解了 Click Counter 程序工作的细节，就可以继续学习第 3 章了。如果你还在纠结一些问题，我建议你花些时间回顾这一章，并在 TextPad、Eclipse 或 NetBeans 中尝试尝试 Click Counter 程序。

以下段落有助于阐明 Click Counter 程序和 JavaFX 中一些可能绊住你的关键点：

> **✓ 程序什么时候从静态（static）切换到非静态（non-static）？** 像每个 Java 程序一样，JavaFX程序的主要入口点是静态 `main` 方法。
>
> 在大多数 JavaFX 程序中，静态 `main` 方法只做一件事：调用 `launch ` 方法启动程序的 JavaFX 部分。`launch` 方法会创建 `ClickCounter` 类的实例，然后调用 `start` 方法。此时，因为已经创建了 `ClickCounter` 类的实例，该程序不再在静态上下文中运行。
>
> **✓ `primaryStage` 变量从哪里来？** 当 `launch ` 方法调用 `launch ` 方法时，`primaryStage` 变量将传递到 `start` 方法。因此，`start` 方法接收 `primaryStage` 变量作为参数。
>
> <img src="assets/technical-stuff.png" width="50"/>这就是为什么你找不到 `primaryStage` 变量的单独声明。
>
> **✓ ->操作符是如何工作的？** `->` 操作符用于创建所谓的 Lambda 表达式。Lambda 表达式是 Java 8 的一个新特性，用于替代以前需要匿名类的情况。如果你不了解 Lambda 表达式的工作原理，不必担心，我会在第 3 章详细解释。

