# Modul 3
Nama    : Clara Sista Widhiastuti<br/>
NPM     : 2206825782<br/>
Kelas   : Pemrograman Lanjut A <br/>


## Reflection
### Explain what principles you apply to your project!
**SRP** </br>
Suatu kelas harus bertanggung jawab pada satu tugas/pekerjaan saja. Kelas-kelas tersebut dipisahkan pada beberapa file 
untuk memudahkan maintainnya. Pada code ini, saya menerapkan prinsip tersebut dengan memisahkan CarController dari 
ProductController. Hal tersebut juga, nantinya akan memudahkan proses mapping. Selain itu, pada method update di 
CarRepository, saya memisahkan dengan menggunakan method findById. Sehingga method update dapat berfokus pada 
pengudatean data saja, karena sebelumnya method update juga bertugas untuk mencari car mana yang akan di update.

<br>**ISP**</br>
Interface terpisah dan fokus bertanggung jawab serta mengerjakan satu pekerjaan. Pada code saya sudah menerapkan prinsip 
tersebut. Pada file CarService sudah terpisah karna memiliki tugas masing-masing dan tidak berhubungan.

<br>**DIP**</br>
Sebuah clas disarankan tidak bergantuk pada konkret class melainkan pada abstrak class. Pada code saya, sudah mengubah 
yang awalnya CarController bergantung pada CarServiceImpl menjadi bergantung pada interface CarService.

<br>**LSP**</br>
Dalam LSP nilai pada class tidak boleh berubah jika di override. Pada code saya, saya tidak mennggunakan inheritance, 
sehingga tidak perlu menerapkan LSP

### Explain the advantages of applying SOLID principles to your project with examples.
Dengan menerapkan prinsip SOLID pada kode saya, maka dalam jangka panjang kode saya menjadi mudah untuk dimaintain. 
Selain itu, jika ingin menambahkan fitur baru akan lebih mudah. Kedepannya untuk memodifikasi kode yang telah dibuat 
untuk melakukan pembaruan juga akan lebih mudah. Kode yang kita buat juga akan mudah untuk dibaca. Contohnya jika kita 
bekerja dalam suatu perusahaan kemudian kode yang kita buat nantinya akan dimaintain atau dilakukan updating oleh tim 
lain, maka tim lain akan lebih mudah untuk menganalisa kode yang kita buat.

### Explain the disadvantages of not applying SOLID principles to your project with examples.
Menurut saya, jika kita tidak menerapkan prinsip SOLID, kedepannya kode akan lebih sulit untuk dimodifikasi dan sulit 
dipahami. Proses testing akan lebih susah dilakukan juga. Karena satu file bisa melakukan testing untuk banyak method, 
ketika testing gagal maka akan sulit menemukan method mana yang gagal. Contohnya jika CarController tetap digabung 
bersama dengan ProductController maka untuk testing akan lebih susah dan rumit. Selain itu proses mapping juga akan 
lebih susah.

<details>
<summary>Modul 2</summary>
### List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
1. Mengubah return delete product</br>
Sebelumnya delete product tidak mereturn apapun, hanya men delete saja, dan saya merasa kesuliatan untuk implementasi 
unit testnya. Kemudian saya megganti delete mereturn boolean.

2. Product ID</br>
Membetulkan algoritma product id, dimana product id dijamin tidak null. Jika null akan meng set product id.

###  Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment?
Menurut saya, implementasi code yang saya buat sudah sesuai dan mengikuti prinsip-prinsip CI/CD. Dengan adanya CI/CD 
memungkinkan untuk adanya building, testing, and deploying secara otomatis. GitHub Actions memungkinkan CI dengan secara 
otomatis menjalankan pengujian dan pemeriksaan setiap kali push kode ke repositori. Hal ini memastikan bahwa perubahan 
tidak merusak fungsionalitas yang ada. Meskipun GitHub Actions lebih berfokus pada CI, kita dapat memperluas untuk 
menangani CD juga. Misalnya, kita dapat memicu penerapan ke environment tertentu (staging) berdasarkan CI yang berhasil 
dijalankan. Untuk pengintegrasian deployment menggunakan PaaS Koyeb.
</details>
<details>
<summary>Modul 1</summary>
## Reflection 1
### Prinsip Clean Code dan Penerapannya
1. Menggunakan penamaan yang sesuai
Contohnya untuk menamai function yang digunakan untuk menambahkan produk baru, maka menggunakan nama 'createProduct'
2. Function
Fungsi yang digunakan cenderung pendek (tidak lebih besar dari satu layar penuh) dan jelas. 
Memastikan pula fungsi melakukan suatu hal dengan baik
3. Comment
Komentar yang dituliskan harus benar-benar suatu komentar yang diperlukan. Seperti komentar yang informatif, 
maupun komentar yang berupa klarifikasi terhadap kode yang tidak dapat diubah
4. Error handling
Eror yang terjadi pada program harus diselesaikan secara efektif dan bukannya malah membingungkan

### How to improve your code
Pertama, aku membaca modul dan isi dari modul juga sudah dijelaskan oleh dosen saat sesi kelas. Selain itu saya juga
memahami source code yang telah diberikan di modul. Saya juga mencari refrensi-refrensi di google maupun website yang
sudah di rekomendasi di modul juga

## Reflection 2
### After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean your code has no bugs or errors?
Untuk membuat suatu unit test harus teliti. Karena kita harus mengetest setiap feature dan kemungkinan yang
terjadi dalam progrem yang telah kita buat. Unit test dibuat seperlunya tergantung kebutuhan di suatu class tersebut.
Untuk memastikan unit test yang dibuat cukup, kita perlu melalkukan berbagai uji coba yang mencakup semua kemungkinana
dan kondisi. Meskipun memiliki code coverage yang tinggi tidak dapat menjamin suatu program tidak miliki bug atau error

### Suppose that after writing the CreateProductFunctionalTest.java along with the corresponding test case, you were asked to create another functional test suite that verifies the number of items in the product list. You decided to create a new Java class similar to the prior functional test suites with the same setup procedures and instance variables.
### What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make the code cleaner!
Clean code sangat berguna jika ingin menambahkan tes fungsional yang baru. Programer nantinya akan lebih mudah untuk
maintanance kedepannya. Jika kode yang dibuat sebelumnya tidak memenuhi prinsip clean code, code akan susah untuk
dibaca lagi dalam jangka wkatu yang panjang. Untuk perbaikannya bisa mengikuti prinsip clean code. Memperjelas nama
variabel, mempersingkan fungtion.
</details>