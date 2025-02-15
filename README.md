Nama : Ischika Afrilla

NPM : 2306227955

### Module Advance Programming - B

Link deployment : [https://eshop-advprog-kaachiii.koyeb.app/](https://eshop-advprog-kaachiii.koyeb.app/)

## Daftar Isi

- [Reflection 1](#reflection-1)
- [Reflection 2](#reflection-2)

### Reflection 1
_Clean code principles_ dan _secure coding practices_ yang sudah saya terapkan pada kode saya:
- Pemberian nama variabel yang bermakna dan tidak ambigu seperti `productID`, `productName`, `productQuantity`, etc. 
- Pemberian nama _method_ yang bermakna dan tidak ambigu seperti _method_ `pageTitle_isCorrect`, `createProduct_isCorrect`, `buttonCreateProduct_isCorrect`, etc.
- Memisahkan program ke dalam berbagai _method/function_ untuk meningkatkan _reusability_ serta menjaga kerapihan kode.
- Tidak mengulang _method_ yang sama di berbagai tempat, seperti _method_ `findProductByID` yang cukup dituliskan sekali dan dapat digunakan untuk _edit_ serta _delete_ produk.
- Tidak berlebihan dalam memberikan _comments_.
- Menggunakan _setter_ dan _getter_ dari pihak ketiga, yaitu `lombok`, untuk mengurangi _code boilerplate_, meningkatkan keterbacaan, serta membuat kode lebih ringkas.
- Mengganti ID produk menjadi UUID untuk meningkatkan keamanan dan mencegah enumerasi saat mengedit atau menghapus produk.
- Memvalidasi _input_ _quantity_ produk agar nilai kurang dari 0 secara otomatis diatur menjadi 0.

Saya merasa ada yang bisa ditingkatkan dalam kode saya, terutama dalam penggunaan _method_ untuk menghapus produk. Saat ini, saya masih menggunakan _method_ `GET` karena lebih mudah, tetapi sebaiknya menggunakan _method_ `POST` atau `DELETE` agar sesuai dengan standar dan lebih aman.

### Reflection 2
1.
- Bagaimana perasaan setelah membuat _unit-test_?

  Setelah membuat dan menerapkan unit test, saya merasa lebih mudah dalam mendeteksi _bug_ sejak dini, sehingga masalah dapat diperbaiki sebelum berdampak lebih luas. Selain itu, _unit test_ membantu memastikan bahwa perubahan atau pembaruan kode tidak merusak fungsi yang sudah ada, sehingga saya lebih percaya diri dalam mengembangkan fitur baru. Dengan adanya _unit test_, proses _debugging_ menjadi lebih cepat dan efisien karena saya dapat langsung mengetahui bagian kode yang bermasalah tanpa harus memeriksa semuanya secara manual.


- Berapa banyak _unit-test_ yang seharusnya dibuat di dalam sebuah _class_?

  Jumlah _unit test_ yang seharusnya dibuat dalam sebuah _class_ tergantung pada kompleksitas dan jumlah fungsionalitas yang ada di dalamnya.


- Bagaimana cara memastikan _unit-test_ cukup untuk memverifikasi program kita?
    - Menggunakan _code coverage_ sebagai indikator.
    - Menguji berbagai skenario yang ada.
    - Memastikan _unit test_ bersifat independen dan konsisten.
    - Menggunakan _static analysis_, seperti SonarQube.
    - Melakukan _code review_ terhadap _unit-test_.


- Apakah jika 100% _code coverage_ maka kode saya tidak memiliki _error/bugs_?

  Tidak, 100% _code coverage_ tidak menjamin kode bebas _bug_. Meskipun semua baris kode telah diuji, kemungkinan untuk _logic error_, _edge case_ yang terlewat, atau _bug_ yang muncul saat integrasi antarkomponen masih ada. _Code coverage_ hanya memastikan kode telah dieksekusi oleh _unit test_, tetapi tidak menjamin hasilnya benar.

2.
- _Cleanliness of the code of the new functional test suite?_

  Kode dalam _functional test suite_ baru kemungkinan kurang bersih karena terdapat duplikasi kode dari _suite_ sebelumnya. Jika _setup_ dan _instance variable_ yang sama digunakan berulang kali di beberapa _test suite_, sebaiknya refaktor kode dengan membuat kelas dasar (_base test class_) yang berisi setup umum. Dengan cara ini, setiap _test suite_ cukup mewarisi kelas dasar tersebut, sehingga kode menjadi lebih rapi, mudah dipelihara, dan mengurangi redundansi.


- Apakah _new code_ akan mengurangi _code quality_?

  Ya, _new code_ dapat mengurangi _code quality_ jika mengandung duplikasi dari _functional test suite_ sebelumnya. Mengulang _setup_ dan _instance variable_ di banyak tempat membuat kode lebih sulit dipelihara dan rentan terhadap inkonsistensi.


- Identifikasi potensi isu _clean code_, jelaskan dengan alasan dan sarankan _improvement_ yang _possible_ untuk membuat _code cleaner_!

  Potensi Isu _Clean Code_:
    - Duplikasi Kode – Mengulang _setup_ dan _instance variable_ di beberapa _test suite_ membuat kode lebih sulit dipelihara.
    - Keterbacaan Rendah – Sulit untuk _debugging_ jika banyak kode yang sama tersebar di beberapa _class_.
    - _Code Bloat_ – Repetisi kode membuat kode lebih panjang dan tidak efisien.
  
  Saran Perbaikan:
    - Menggunakan _Inheritance_ – Buat _BaseTestSuite_ yang berisi setup umum, lalu buat _test suite_ baru yang mewarisinya.
    - Menggunakan _Utility Class_ – Jika setup tidak perlu diwarisi, gunakan _helper class_ atau _static method_ untuk menghindari duplikasi.
    - Menggunakan _Parameterized Test_ – Jika _test case_ serupa, gunakan _JUnit Parameterized Test_ untuk menghindari pengulangan kode.

### Reflection 3
