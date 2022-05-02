# 第 7 章 JavaFX 节点层级介绍

> **在这一章当中r**
>
> + 介绍构成 JavaFX 的最重要的包和
> + 查看所有控件通过 Node、Parent 和 Region 类继承的重要方

JavaFX 控件的最简单定义是：控件是从直接或间接继承 JavaFX 控件类的类创建的对象。 Control 类提供了将 JavaFX 对象视为控件所需的所有基本功能。例如，任何继承 Control 类的类在场景中都有可视化表示，可以添加到布局窗格中，可以在通过调用 setMaxWidth 或 setMinHeight 等方法设置的参数范围内自动调整其大小，并且可以有一个工具提示当用户将鼠标悬停在控件上时弹出。

尽管所有控件都具有这些共同特性，但并非所有这些特性都由 Control 类直接提供。那是因为 Control 类本身继承了 Region 类，Region 类又继承了 Parent 类，而 Parent 类又继承了 Node 类。沿着这个继承链的每个类都为每个 JavaFX 控件贡献了特性。

在本章中，您将了解每个 JavaFX 控件共有的一些更重要的特性，因为每个控件都继承了 Control 类，而 Control 类又继承了 Region、Parent 和 Node 类。

## JavaFX 包概述

在查看构成 Node 类层次结构的类之前，我想简要讨论一下构成 JavaFX 的各种包。 JavaFX 本身由 665 个类组成，这些类分布在 36 个不同的包中，这些包都以根名称 javafx 开头

到目前为止，您已经在本书中看到了来自以下七个包的 JavaFX 类

> ✓ javafx.application：这个包中最重要的类是
>
> 应用程序，它提供 JavaFX 应用程序的基本生命周期功能。
>
> 正如我在第 2 章中所讨论的，所有 JavaFX 程序都扩展了 Application 类并实现了 start 方法，该方法被调用来启动应用程序。 Application 类还创建应用程序的主要阶段，并通过 primaryStage 参数将其传递给 start 方法。这允许程序在应用程序的窗口中显示场景。
>
> ✓ javafx.stage：这个包中最重要的类是Stage，
>
> 表示可以显示用户界面的窗口。您在第 4 章中了解了 Stage 类。该类中还有其他类可能偶尔有用，例如 FileChooser 和 DirectoryChooser，它们显示允许您选择文件和目录的对话框。
>
> ✓ javafx.scene：这个包包含几个重要的类，它们处理创建可以在舞台中显示的用户界面场景。这个包中最重要的两个类是
>
> - 场景，创建场景对象。
>
> 您可以在第 4 章中阅读有关 Scene 类的信息。
>
> - 节点，它是场景中包含的所有对象的基类，包括控件和布局窗格。
>
> 有关 Node 类的更多信息，请参阅本章后面的“Node 类”部分。
>
> ✓ javafx.scene.control：该包包含大部分JavaFX的用户界面控件类，包括Button、Label、CheckBox和RadioButton。此包中还包括 Control，它是派生所有用户界面控件的基类。有关 Control 类的更多信息，请参阅本章后面的“Control 类”部分。 （注意：其他包中定义了一些 JavaFX 控件，包括 javafx.scene.control.cell 和 javafx.scene.web。）
>
> ✓ javafx.scene.layout：这个包包含布局窗格类，
>
> 例如 HBox、VBox 和 BorderPane。此包中定义的另外两个重要类是 Pane 和 Region。所有的布局窗格类都是基于 Pane 类的，这个包中的 Pane 类和 javafx.scene.control 类中的 Control 类都是基于 Region 类的。有关 Region 类的更多信息，请参阅本章后面的“Region 类”部分。
>
> ✓ javafx.geometry：这是一个相对较小的包，它定义了几个与 JavaFX 节点的几何形状相关的类和枚举。在第 5 章中，您将了解如何使用 Insets 类来控制布局窗格中的间距，以及如何使用 Pos 枚举来指定对齐方式。
>
> ✓ javafx.collections：这个包定义了 ObservableList
>
> 类，由 Pane 类的 getChildren 方法使用。在接下来的几章中，您还会遇到需要此包的几个控件类。

因为这七个包包含您将在主要与控件一起使用的应用程序中使用的大多数 JavaFX 类（与与其他用户界面对象（如图形、形状或动画）一起使用的类相反），我建议您导入所有所有程序中这七个包中的类：

```java
import javafx.application.*; 
import javafx.stage.*; 
import javafx.scene.*; 
import javafx.scene.control.*; 
import javafx.scene.layout.*; 
import javafx.scene.geometry.*; 
import javafx.collections.*;
```

尽管您不需要每个程序中所有这些包中的所有类，但每次都包括这些整个包，就无需跟踪哪些程序需要哪些特定类。

在本章的其余部分，我将仔细研究所有 JavaFX 控件继承的类：Node、Parent、Region 和 Control。

## 节点类

JavaFX Control 类层次结构始于 Node 类，它表示可以添加到 JavaFX 场景的对象。嗯，实际上，Control 类层次结构中最顶层的类是 Object 类，但这里几乎不值得一提，因为所有 Java 类层次结构都以 Object 类开头——Object 是所有 Java 类的母亲。

作为场景一部分的所有对象都属于场景图，这是一个包含构成用户界面的所有节点的树结构。要成为场景图的一部分，对象必须继承 Node 类。因此，Node 类是可以添加到 JavaFX 场景的所有类的基类。

与任何其他树结构一样，场景图以单个节点（根节点）开始，它可以有一个或多个子节点，每个子节点又可以有一个或多个子节点。具有至少一个子节点的节点为分支节点；没有子节点的节点是叶节点。一个场景只能有一个根节点，但它可以有很多分支和叶子节点。

Node 类是一个抽象类，这意味着您不能直接创建它的实例。换句话说，以下代码会导致编译器错误：

```java
Node myNode = new Node();
```

但是，您可以使用 Node 类来保存您不确定或不关心的类型的节点。例如：

```java
Node myNode = new Button();
```

在本例中，myNode 变量是 Node 类型，但它用于保存对 Button 控件的引用。

Node 层次结构上下的许多类方法接受或返回 Node 类型的对象。例如，与 HBox 和 FlowPane 等布局窗格一起使用的 getChildren 方法返回 Node 对象的列表。用于将对象添加到布局窗格的节点列表的 add 方法接受 Node 对象作为参数。换句话说，任何 Node 对象都可以添加到布局窗格中。

表 7-1 仅列出了您可能在大多数 JavaFX 程序中使用的一些方法。请注意，此表极大地简化了 Node 类的复杂性。这个类实际上定义了300多个方法。其中超过三分之一与事件处理有关：Node 类是负责所有节点的大多数事件处理的类，包括处理鼠标、键盘和与节点的触摸屏交互的事件。

**表 7-1 节点类常用方法**

| 方法                      | 解释                                                  |
| --------------------------- | ------------------------------------------------------------ |
| Parent getParent()          | 返回此节点的父节点。 |
| String getId()              | 返回此节点的 ID。 |
| void setId(String id)       | 设置此节点的 ID。 ID 在场景图中应该是唯一的。 |
| Node lookup(String id)      | 在节点的子节点中搜索 ID 与参数匹配的节点。 |
| String getStyle()           | 返回节点的 CSS 样式字符串。 |
| void setStyle(String style) | 设置节点的 CSS 样式字符串。 |

getParent 方法返回节点的父节点。此方法返回一个 Parent 类型的对象，它是一个可以有子节点的节点（正如您在下一节中发现的那样）。场景图中除根节点外的每个节点都必须有一个父节点，并且该父节点的类型始终为 Parent。如果在根节点调用该方法，将返回null。

请注意，每个节点都可以有一个唯一的字符串标识符，这使得在复杂的场景图中很容易区分节点，并且在使用 CSS 格式化场景时也很有帮助。您可以通过调用 setID 方法来设置字符串标识符，该方法接受如下字符串参数：

```java
myNode.setId("LBL3");
```

这里，字符串 LBL3 与节点相关联。

稍后您可以通过调用查找方法找到此节点。这种方法有点古怪，因为您必须在要查找的 ID 前面加上井号 (#)。例如，您可以在整个场景图中搜索 ID 为 LBL3 的控件：

```java
Node myNode = scene.getRoot().lookup("#LBL3");
```

这里调用了场景变量（我假设是Scene类型）的getRoot方法来获取场景的根节点。然后调用lookup方法返回ID为LBL3的节点。

Node 类还有许多其他方法可以让您为所有类型的节点应用通用格式或其他功能。例如，setStyle 方法允许您将 CSS 样式格式应用于任何类型的节点。 setRotate 方法可以让您旋转任何节点。您可以在本书后面的章节中了解这些和其他 Node 方法。

## 父类

尽管有十个不同的类直接继承 Node 类，但在使用 JavaFX 控件时唯一需要关注的是 Parent 类。有关 Node 的所有十个子类的解释，请参见本章侧边栏“十种不同类型的节点”。

Parent 类具有 Node 类的所有功能，以及具有子节点的附加功能。它的主要工作是管理一个子节点的集合，它表示为一个标准的 Java 列表。您可以通过调用 getChildren 方法访问此列表。

您已经在布局窗格（例如 HBox 和 VBox）中看到了这种方法。例如，以下代码创建一个 HBox 窗格，然后向其中添加两个控件：

```java
Label lblAddress = new Label("Address:"); 
TextField txtAddress = new TextField(); 
HBox hbox = new HBox(); 
hbox.getChildren().addAll(lblAddress, txtAddress);
```

getChildren 方法返回一个 ObservableList 类型的对象，该对象又扩展了 List 接口。在它们之间，这两个接口定义了几十个方法，您可以使用它们来操作父节点的子节点。表 7-2 列出了其中一些较常用的方法。

<img src="assets/technical-stuff.png" width="50"/>有趣的是，getParent 方法是在 Parent 类中定义的，具有受保护的访问权限。这意味着尽管getParent 方法对任何继承Parent 类的类都可用，但getParent 方法对外界是不可访问的。要使 getParent 方法成为公共方法，继承 Parent 类的类必须覆盖具有公共访问权限的 getParent 方法。

**表 7-2 常用的 ObservableList 方法**

| 方法                     | 解释                                                |
| -------------------------- | ---------------------------------------------------------- |
| void add(Node node)        | 将单个子节点添加到现有的子节点列表。 |
| void addAll(Node nodes...) | 添加多个子节点。 |
| void remove(Node node)     | 从子列表中删除指定的节点。 |
| void clear()               | 删除所有子节点。 |
| int size()                 | 返回子节点的数量。 |

这正是 Pane 类所做的。 Pane 类是所有布局窗格的基类；它继承了 Parent 类，然后重写了 getParent 方法。这是 Pane 类的实际代码片段：

```java
@Override public ObservableList<Node> getChildren() { 
  return super.getChildren(); 
}
```

如您所见，Pane 类中的 getChildren 方法只是调用其超类（Parent）的 getChildren 方法并返回结果。

<img src="assets/tip.png" width="50"/>一个派生自Parent 类的类，您有时可能会用到它是Group 类。 Group 类有点像 HBox 或 FlowPane 等布局窗格，只是它不为其包含的子节点提供任何实际布局。创建组时，可以将子节点传递给构造函数，如下所示：

```java
Group group = new Group(Node1, Node2, Node3);
```

或者，您可以使用默认构造函数并通过 getChildren 方法添加子节点，例如 这:

```java
Group group = new Group(); 
group.getChildren().addAll(Node1, Node2, Node3);
```

您会在本书中偶尔看到组节点的示例。

> **十种不同的节点**
>
> 总共有十个不同的类直接继承 Node 类，在 Node 下创建了十个不同的继承分支。我在本书这一部分的章节中讨论的这十个中唯一的一个是 Parent 类，因为所有 JavaFX 控件和布局窗格都派生自 Parent 类。但是，为了让您大致了解除了控件和布局窗格之外还有哪些其他类型的对象可以添加到场景图中，以下段落简要总结了 Node 的十个子类中的每一个提供的内容：
>
> ✓ 相机：用于在平面显示器上以图形方式呈现 3D 屏幕的对象。
>
> 相机是一个节点，因为在表示 3D 布局的场景图中，相机可以定位在该布局内的特定位置，从而从特定角度渲染 3D 布局的平面图像。
>
> ✓ 画布：您可以使用绘图命令在其上绘制的节点，就像艺术家可以在画布上绘制以创建绘画一样。
>
> 画布是具有高度和宽度的二维对象。
>
> ✓ ImageView：表示图像查看器，用于显示二维图像。
>
> ✓ LightBase：一个抽象类，用作照明由相机渲染的场景的光源的基类。
>
> 与相机一样，光源是一个节点，因此您可以将其放置在场景中的特定位置以创建逼真的照明效果。
>
> ✓ MediaView：表示可以播放媒体的媒体查看器，例如声音或视频。
>
> ✓ Parent：可以包含子节点的节点。所有控件和布局窗格都继承了 Parent 类。
>
> ✓ 形状：二维形状，如矩形或圆形。
>
> Text 类还继承了 Shape 类，提供了一种在场景中显示文本的简单方法。
>
> ✓ Shape3D：一种三维形状，例如长方体、圆柱体或球体。
>
> ✓ 子场景：标记场景的分支
>
> 可以用自己的相机渲染。
>
> ✓ SwingNode：允许您将 Swing 对象合并到 JavaFX 场景图中。
## 区域类

Node 类层次结构中的下一个是 Region 类。 Region 是 Control 类和 Pane 类共享的最后一个公共祖先类。因此，Region 类是控件和布局窗格共享共同特征的最后一个类。

顾名思义，Region 类定义了具有物理尺寸的场景的可见区域——即高度和宽度。区域的大小取决于许多因素，但默认情况下将由其包含的内容的大小决定。您可以设置区域将遵循的最小、最大和首选大小约束，并且您可以指定固定数量的填充，以在区域内容与其外边缘之间提供边距。此外，区域的视觉风格可以通过层叠样式表来设置。

表 7-3 显示了 Region 类提供的最常用的方法，这些方法都与设置区域的大小有关。

**表7-3 Region类常用方法**

| 方法                            | 解释                                           |
| --------------------------------- | ----------------------------------------------------- |
| void setMaxHeight(double height)  | 设置区域的最大高度。 |
| void setMinHeight(double height)  | 设置区域的最小高度。 |
| void setPrefHeight(double height) | 设置区域的首选高度。 |
| void setMaxWidth(double width)    | 设置区域的最大宽度。 |
| void setMinWidth(double width)    | 设置区域的最小宽度。 |
| void setPrefWidth(double width)   | 设置区域的首选宽度。 |
| double getHeight()                | 获取区域的实际高度。 |
| double getWidth()                 | 获取区域的实际宽度。 |
| void setPadding(Insets value)     | 设置 Hbox 内边缘周围的填充。 |

Region 类提供了三个不同的参数，可让您控制区域的高度和宽度。对于高度和宽度，您可以设置最小值、首选值和最大值。正如它们的名字所暗示的那样，JavaFX 不会使控件小于最小尺寸或大于最大尺寸，并且如果可能，会选择首选尺寸。

在这些参数中，JavaFX 将根据其包含的内容确定区域的理想高度和宽度。对于控件，例如标签或按钮，内容是标签或按钮显示的文本。对于更复杂的控件，内容更复杂。对于布局窗格，内容由添加到窗格的所有节点的聚合组成。

如果您不指定任何高度或宽度约束，则所有三个（最小值、首选和最大值）都默认为控件内容的实际计算大小。

注意：在许多情况下，区域的内容会自动调整大小以填充可用的空间。因此，如果用户动态调整包含场景的窗口的大小，则场景中包含的所有区域的大小可能会扩大或缩小以填充可用空间。

<img src="assets/tip.png" width="50"/>如果要为区域的宽度或高度设置精确值，请将所有三个参数（最小值、首选和最大值）设置为相同的值。例如：

```java
lbl.setMinWidth(150); 
lbl.setPrefWidth(150); 
lbl.setMaxWidth(150);
```

在这种情况下，您可能更喜欢创建一个常量：

```java
final static int LABEL_WIDTH = 150; 
lbl.setMinWidth(LABEL_WIDTH); 
lbl.setPrefWidth(LABEL_WIDTH); 
lbl.setMaxWidth(LABEL_WIDTH);
```

这样一来，如果您改变了对标签宽度的看法，您只需要更改一处的值。

另一项设置会影响区域的高度或宽度：您指定的填充量。填充提供区域边缘周围的边距，以防止看起来拥挤的场景。注意：与控件相比，您更有可能在布局窗格中使用填充。

要指定填充，请使用在 javafx.xml 中定义的 Insets 类。几何包。 Insets 提供了两个构造函数。第一个允许您在区域的所有四个边上设置均匀的边距：

```java
pane.setPadding(new Insets(10));
```

或者，您可以为顶部、右侧、底部和左侧边缘设置不同的值：

```java
pane.setPadding(new Insets(10,0,10,0);
```

在此示例中，顶部和底部边距设置为 10 像素，但右侧和左侧边距设置为 0。

有关填充的更多信息，请翻到第 5 章。

## 控制类

本章的最终目的是概述所有 JavaFX 控件共有的特性，因为所有控件都继承了 Control 类。既然您终于讨论了 Control 类本身，请做好准备，有点失望：Control 类本身并没有那么有趣。如表 7-4 所示，实际上只有三种方法感兴趣。事实证明，所有控件共有的大部分功能实际上都是由 Region、Parent 和 Node 控件提供的。

**表 7-4 控制类的方法**

| 方法                                 | 解释                                                  |
| -------------------------------------- | ------------------------------------------------------------ |
| void setTooltip(Tooltip value)         | 设置控件的工具提示                              |
| void setContextmenu(Contextmenu value) | 设置控件的上下文菜单。有关详细信息，请参阅第 10 章。 |
| void setSkin(Skin value)               | 设置控件的外观。有关详细信息，请参阅第 12 章。 |

Control 向 Region 类添加了三个主要功能：添加工具提示、上下文菜单和 CSS 皮肤的能力。您可以在第 10 章阅读上下文菜单，并在第 12 章了解 CSS 皮肤的工作原理。所以现在，我只看工具提示。

工具提示是提供控件功能说明的弹出气球。创建工具提示再简单不过了：调用 Tooltip 构造函数，将工具提示的文本作为参数传递，然后通过调用控件的 setTooltip 方法将工具提示分配给控件。这是一个例子：
```java
btnSave.setTooltip(new Tooltip("Saves the file"));
```

然后，当用户将鼠标悬停在按钮上时，会出现工具提示。

恭喜！您现在知道了所有 JavaFX 控件都可以使用的最重要的方法，因为它们都继承了 Control 类，而后者又继承了 Region，继承了 Parent，继承了 Node。

现在，在本部分的其余章节中，您将了解如何使用一些最常用和最有用的 JavaFX 控件，包括单选按钮、复选框、选择框、列表、树视图、表格和菜单。