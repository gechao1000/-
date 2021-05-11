下载

https://mirrors.tuna.tsinghua.edu.cn/CTAN/systems/texlive/Images/texlive2021-20210325.iso



基本语法

http://www.biexiaoyu1994.com/实用工具/2019/10/21/latex_summary/



VSCode 插件

```
LaTeX Workshop
LaTeX Utilities

--- 配置
"latex-workshop.latex.tools": [
	{
		"name": "xelatex",
		"command": "xelatex",
		"args": [
			"-synctex=1",
			"-interaction=nonstopmode",
			"-file-line-error",
			"%DOCFILE%"
		]
	},    


"latex-workshop.latex.recipes": [
	{
		"name": "xelatex",
		"tools": [
			"xelatex"
		]
	},
	
--- 格式化
右键 -> 格式化文档的方式
```





示例

```latex
\documentclass[UTF8]{ctexart}
    \title{文章标题}
    \author{David}
    \date{\today}
\begin{document}
\maketitle
This is the context of the article.
\end{document}
```

