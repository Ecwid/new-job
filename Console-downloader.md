Консольная утилита для скачивания файлов по HTTP протоколу.

Входные параметры:

    -n количество одновременно качающих потоков (1,2,3,4....)
	-l общее ограничение на скорость скачивания, для всех потоков, размерность - байт/секунда, можно использовать суффиксы k,m (k=1024, m=1024*1024)
	-f путь к файлу со списком ссылок
	-o имя папки, куда складывать скачанные файлы

Формат файла со ссылками:

	<HTTP ссылка><пробел><имя файла, под которым его надо сохранить>

пример:

	http://example.com/archive.zip my_archive.zip
	http://example.com/image.jpg picture.jpg
	......


В HTTP ссылке нет пробелов, нет encoded символов и прочей ерунды - это всегда обычные ссылки с английскими символами без специальных символов в именах файлов и прочее. Короче - ссылкам можно не делать decode. Ссылки без авторизации, не HTTPS/FTP - всегда только HTTP-протокол.

Ссылки могут повторяться в файле, но с разными именами для сохранения, например:

	http://example.com/archive.zip first_archive.zip
	http://example.com/archive.zip second_archive.zip

Одинаковые ссылки - это нормальная ситуация, хорошо бы ее учитывать.

В конце работы утилита должна выводить статистику - время работы и количество скачанных байт.

Утилита должна быть написана на Java (версия 7 или выше, по желанию) или Kotlin.
Для сборки проекта необходимо использовать ant/gradle.

Пример вызова:

	java -jar utility.jar -n 5 -l 2000k -o output_folder -f links.txt

По всем вопросам смело писать на vgv@ecwid.com


<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-60374351-1', 'auto');
  ga('send', 'pageview');

</script>
