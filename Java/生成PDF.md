## Spire.XLS

> https://www.e-iceblue.cn/

收费，不开源

可以将XLS转为PDF

免费版限制非常大

```
Free version is limited to 5 sheets per workbook and 200 rows per sheet. This limition is enforced during reading or writing XLS or PDF files.
When converting Excel files to PDF files, you can only get the first 3 pages of PDF file.
```

## EasyPOI

> https://gitee.com/lemur/easypoi
>
> http://www.wupaas.com/

Excel可以使用模板导出

## ASPOSE

> https://products.aspose.com/cells/java

收费

可以将XLS转为PDF

有水印，大小限制，时间限制

## Wkhtmltopdf,  Wkhtmltoimage

1. html 转换为 pdf

2. html 转换为 jpg，再用itext7生成pdf文件

```
<dependency>
	<groupId>com.itextpdf</groupId>
	<artifactId>layout</artifactId>
	<version>7.1.11</version>
</dependency>
```

```
Image image = new Image(ImageDataFactory.create("index.jpg"));
PdfDocument pdf = new PdfDocument(new PdfWriter("out.pdf"));

Document doc = new Document(pdf, new PageSize(image.getImageWidth(), image.getImageHeight()));
doc.add(image);
doc.close();
```

