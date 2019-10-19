Парсер SQL запросов

В SQL самым синтаксически сложным и навороченным является, пожалуй, запрос SELECT. Явные и неявные объединения, группировки, 
подзапросы, сортировки и усечения выборки – вся эта красота может встречаться неоднократно даже в одном единственном 
select-запросе.

Например, так:
```sql
SELECT * FROM book
```
или так:
```sql
SELECT author.name, count(book.id), sum(book.cost) 
FROM author 
LEFT JOIN book ON (author.id = book.author_id) 
GROUP BY author.name 
HAVING COUNT(*) > 1 AND SUM(book.cost) > 500
LIMIT 10;
```	

Напишите, пожалуйста, парсер произвольного SELECT-запроса, представляющего его в виде класса примерно такого структуры:
```java
class Query {
	private List<String> columns;
	private List<Source> fromSources;
	private List<Join> joins;
	private List<WhereClause> whereClauses;
	private List<String> groupByColumns;
	private List<Sort> sortColumns;
	private Integer limit;
	private Integer offset;
}
```

Структура этого класса не догма, а руководство к действию :) 
Если вы придумали класс, лучше отражающий структуру SQL-запроса чем приведено здесь – смело используйте свое решение. 

Какие конструкции парсер должен поддерживать в обязательном порядке:
- Перечисление полей выборки явно (с алиасами) или *
- Неявное объединение нескольких таблиц (select * from A,B,C)
- Явное объединение таблиц (inner, left, right, full join)
- Фильтрующие условия (where a = 1 and b > 100)
- Подзапросы (select * from (select * from A) a_alias)
- Группировка по одному или нескольким полям (group by)
- Сортировка по одному или нескольким полям (order by)
- Усечение выборки (limit, offset)

На что можно не обращать внимание:
- Дополнение выборок (union и union all)
- CTE
- Window-функции


Выполнять работу необходимо на Kotlin или Java (версия 11 или выше). По всем вопросам смело писать на vgv@ecwid.com, сделанное задание отправлять туда же.
