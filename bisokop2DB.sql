use bioskop2;

create table film_detail(
	id_film int not null AUTO_INCREMENT,
    nama_film varchar(255),
    genre_film varchar(50),
    cast_film varchar(255),
    tanggal_tayang DATE not null,
    jam_tayang TIME,
    harga_tiket int,
    PRIMARY KEY(id_film)
	);
   
insert into film_detail(nama_film, genre_film, cast_film, tanggal_tayang, jam_tayang, harga_tiket)
values(
	'Finding Dory',
    'Comedy, Cartoon',
    'Ellen DeGeneres',
    '2021-07-12',
    '10:30:00',
    55000
	);

-- show daftar film
select * from film_detail;

-- show tanggal tayang dari paling terakhir
select * from film_detail fd
order by fd.tanggal_tayang desc;

-- show film dengan tiket termurah
select * from film_detail fd
order by fd.harga_tiket asc
LIMIT 3;
