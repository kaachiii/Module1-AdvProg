Nama : Ischika Afrilla

NPM : 2306227955

Module Advance Programming - B

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
- Menerapkan _error handling_ pada bagian _test_, seperti `assertNull` dan `assertNotNull`.
- Mengganti ID produk menjadi UUID untuk meningkatkan keamanan dan mencegah enumerasi saat mengedit atau menghapus produk.
- Membuat berbagai _unit test_ dan _functional test_ untuk memastikan setiap komponen aplikasi bekerja dengan benar dan sesuai dengan spesifikasi.
- Memvalidasi _input_ _quantity_ produk agar nilai kurang dari 0 secara otomatis diatur menjadi 0.

### Reflection 2