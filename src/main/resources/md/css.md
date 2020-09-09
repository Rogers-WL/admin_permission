**https://www.w3school.com.cn/css/css_syntax_descendant_selector.asp**
# 样式引入


```html
<head>
<!--多重样式选择更具体的样式属性-->
<!--外部样式表，以.css后缀-->
<link rel="stylesheet" type="text/css" href="mystyle.css" />
<!--内部样式表-->
<style type="text/css">
  hr {color: sienna;}
  p {margin-left: 20px;}
  body {background-image: url("images/back40.gif");}
</style>
</head>
<body>
<!--内联样式-->
<p style="color: sienna; margin-left: 20px">
This is a paragraph
</p>
</body>
```


# 选择器

- 1.选择器

    h1,h2,h3,h4,h5,h6 {
        color: green;
    }
- 2.派生选择器

    <!--li标签下的strong-->
    li strong {
        font-style: italic;
        font-weight: normal;
    } 
- 3.id选择器

```html
<style type = "text/css">
/* 选择id=red的标签 */
#red {color:red;}
/* id选择器一般和派生选择器一起使用，选择id=sidebar的标签下的p和h2标签 */
#sidebar p {
    font-style: italic;
    text-align: right;
    margin-top: 0.5em;
    }
#sidebar h2 {
    font-size: 1em;
    font-weight: normal;
    font-style: italic;
    margin: 0;
    line-height: 1.5;
    text-align: right;
	}
</style>
```
- 4.类选择器

```html
<style type = "text/css">
/* 选择class=center的标签 */
.center {text-align: center}
/* 类似于id选择器，可以和派生选择器结合，选择fancy下的td */
.fancy td {
    color: #f60;
	background: #666;
    }    
/* 选择class=fancy的td元素 */
td.fancy {
    color: #f60;
    background: #666;
    }   
</style>
```
- 5.属性选择器

```html
<head>
<style type="text/css">
[title~=hello]
{
    color:red;
} 
</style>
</head>
<body>
<h1>可以应用样式：</h1>
<h2 title="hello world">Hello world</h2>
<p title="student hello">Hello W3School students!</h1>
<hr />
<h1>无法应用样式：</h1>
<h2 title="world">Hello world</h2>
<p title="student">Hello W3School students!</p>
</body>
```
# css样式

- 像素px是相对于显示器屏幕分辨率而言的
- em是相对长度单位。相对于当前对象内文本的字体尺寸。如当前对行内文本的字体尺寸未被人为设置，则相对于浏览器的默认字体尺寸
- rem是[CSS3](http://www.html5cn.org/portal.php?mod=list&catid=16)新增的一个相对单位（root em，根em）,区别在于使用rem为元素设定字体大小时，仍然是相对大小，但相对的只是HTML根元素。这个单位可谓集相对大小和绝对大小的优点于一身，通过它既可以做到只修改根元素就成比例地调整所有字体大小，又可以避免字体大小逐层复合的连锁反应

## 背景图片

```html
<style> 
	body {
		background-image: url(/i/eg_bg_04.gif);
		/* 图像不够大就重复铺满 repeat */
		background-repeat: repeat-y ( no-repeat );
		/* 出现一个关键字，则认为另一个关键字是 center。*/
		background-position:center;
		/* 网页下滑滚动scroll */
		background-attachment:fixed
	}
</style>
```

## 文本

```html
<style>
    p {
		/* 设置缩进，百分数要相对于缩进元素父元素的宽度 */
		text-indent: 5em;
		/* 水平对齐属性  justfy两端对齐 */
		text-align:center;		
		/* 文字或单词之间的间隔，默认0 */
		word-spacing: 30px;
		/* 字符或字母的间隔 */	
		letter-spacing: 20px;
		/* none,uppercase,lowercase,capitalize首字母大学 */
		text-transform: uppercase;
		/* underline,overline,line-through,blink 闪烁 */
		text-decoration: underline overline;
		/* 文本方向，只有两个值ltr，rtr。行内元素，只有当 unicode-bidi 属性设置为 embed 			或 bidi-override 时才会应用 direction 属性。*/
		direction: ltr;
		/* 丢掉多余的空白符,pre，浏览器将会注意额外的空格，甚至回车,nowrap，它会防止元素中		的文本换行，除非使用了一个 br 元素 */
		white-space: normal;
    }
</style>
   
```

# 链接

```html
<style>
a:link {text-decoration:none;}    /* 未被访问的链接 */
a:visited {text-decoration:none;} /* 已被访问的链接 */
a:hover {text-decoration:underline;}   /* 鼠标指针移动到链接上 */
a:active {text-decoration:underline;}  /* 正在被点击的链接 */
</style>
```

# 列表







