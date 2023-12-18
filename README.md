# About The project
Spring Web App program which finds a specific string/phrase in files.

The program works as follow, the input of the program should be a folder or a file, the string 
and in which type of files should the search be applied. In case a folder is given as an input - 
the program should search also in all nested folders(traverse the filesystem). If archive is given
as input it should search for any of the supported file types inside it.

Supported types are:
- plain/text
- html(Jsoup parser is used)
searching could be 1+gb
- binary

Also If multiple occurrence are found, all of them appear in the result.

# About the implementation

File system traversing code is working through making DFS in the root folder, and getting
all possible links which could have files for opening.
There's 2 available API's for testing, which can be found in the controller class.
server.port=8000 , to avoid conflict while using jenkins

API request: GET localhost:8000/search?location=\<location\>&text=\<string\>&type=\<plain/html/xml/binary\>
Expected output: location + list of indexes

Everytime when get request is made to that endpoint, the request is saved in DB
with info about IP, location and text for searching.
Can be checked the saved request by reaching: localhost:8000/auditing

Project is using gradle for build tool, so by executing ./gradlew build project
can be build successfuly.






  
