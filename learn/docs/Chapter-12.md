# 第 12 章 使用 CSS 为程序换肤

> **In This Chapter**
>
> - Applying styles in several ways 
> - Creating your own style sheet
> - Controlling fonts through font styles
> - Creating fills and borders with CSS

One of the most powerful features of JavaFX is its ability to use CSS (which stands for Cascading Style Sheets) to control the visual appearance of your user interface. With CSS, you can change the look and feel of your application without actually changing any of the Java code that powers your application. CSS essentially disconnects the visual aspects of your program from the application logic.

The terms theme and skin are used somewhat interchangeably to refer to the look and feel of an application. A theme or skin governs many aspects of visual appearance, including the font used for text, background fills, border styles and colors, how items react when the mouse is hovered over them, and many more.

In this chapter, I first discuss how to switch an entire application between two of the default themes provided with JavaFX. Then, you discover how to craft your own style sheets and apply them to your scenes.

## Using Default Style Sheets

JavaFX comes with two built-in themes: Modena and Caspian. Modena is a new theme that was introduced with JavaFX 8; Caspian is an older theme that was used with previous versions of JavaFX.

Figure 12-1 shows a version of the Pizza Order application that I present in Chapter 11; it includes a pair of radio buttons to allow the user to switch between the Modena and Caspian themes. The window on the left side of the figure shows the Modena theme; the Caspian theme is shown on the right.

