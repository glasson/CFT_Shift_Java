<b>Java version - 20.0.1</b>
<b>Для сборки использовался Maven v3.8.1</b>

<b>Зависимости
    - Apache Commons Lang v3.14.0 (https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.14.0)

### Использование
    - java -jar [flags] paths_to_files
#### Флаги
    - <b>-o <path></b>. Используется для указания пути к файлам с результатом, после флага нужно указать путь к папке где должны лежать файлы. По умолчанию будут созданны в той же директории где лежит исполняемый файл. 
    - <b>-p <prefix></b>. Используется для добавления превикса к названию файлов с результатом.
    - <b>-a</b>. Используется для того чтобы существующие файлы не перезаписывались а дополнялись. Если файлов нет то они будут созданны.
    - <b>-s</b>. При использовании после выполнения программы будет выведено количество строк с каждым типом данных.
    - <b>-f</b>. При использовании после выполнения программы будет выведено количество строк с каждым типом данных. Для чисел - минимальное и максимальное значения, сумма и среднее. Для строк - длина самой длинной и короткой строк.

#### Пример
    - java -jar file-content-filter-jar-with-dependencies.jar -f -o result/ -p result_ -a ./data/in1.txt ./data/in2.txt
