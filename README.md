# Compression Analysis
## Compression Table
Filename|Original Size|Original Compression| N-Compression | R-Compression | Mac-Compression
---------|--------|---------------- |------------|------------|------------
all.tar | 3MB|1.8MB |1.8MB| 1.2MB| 969KB
assig2.doc | 87KB|75KB | 40KB |40KB |23kB
bmps.tar | 1.1MB| 925KB  | 40KB | 81 KB | 64KB
code.txt | 72KB | 31KB| 25KB | 25KB | 15KB
code2.txt | 58KB| 24KB | 21KB| 21KB | 14KB
edit.exe | 236 KB| 251KB | 156KB | 152KB | 127KB
frosty.jpg | 127KB| 177KB | 164KB | 171KB | 127KB
gone_fishing.bmp.Z | 9KB| 13KB | 13KB | 13KB | 9KB
large.txt | 1.2 MB| 605KB | 502KB | 528KB | 493KB
Lego-big.gif | 93KB | 129KB | 122KB | 122KB | 93KB
medium.txt | 25KB| 13KB | 13KB | 13KB | 11KB
texts.tar | 1.4MB | 1MB| 598KB | 591KB| 534KB
wacky.bmp | 922KB| 4KB | 4KB | 4KB | 3KB
winnt256.bmp | 157KB | 159 KB| 63KB | 63KB | 50KB



Filename|Original-Compression Ratio| N-Compression Ratio | R-Compression Ratio | Mac-Compression Ratio
--------|---------------- |------------|------------|------------
all.tar| 1.67 |1.67| 2.5| 3.2
assig2.doc| 1.16 | 2.2 |2.2 | 3.8
bmps.tar| 1.2 | 28.2 | 13.9 | 17.6
code.txt |2.3| 2.9 | 2.9 | 4.8
code2.txt| 2.4 | 2.8| 2.8 | 4.1
edit.exe| 0.9 | 1.5 | 1.6 | 1.9
frosty.jpg| 0.7 | 0.8 | 0.7 | 1
gone_fishing.bmp.Z| 0.7 | 0.7 | 0.7 | 1
large.txt | 2.03 | 2.4 | 2.3 | 2.49
Lego-big.gif | 0.7 | 0.8 | 0.8 | 1
medium.txt | 1.9 | 1.9 | 1,9 | 2.3
texts.tar | 1.4| 2.4 | 2.4| 2.7
wacky.bmp | 230.5 | 230.5 | 230.5 | 307.3
winnt256.bmp|  0.99| 2.5 | 2.5 | 3.14

## Analysis
In general, the compression Ratio of the N-Compression, R-Compression and Mac-Compression are much better than the original-Compression. Based on the data from the table, almost all the Compressions have greater compression ratio than the Original- compression.From my perspective, the main reason is that the Original compression did not use the variable length codeword. All the codeword in the Original compression have codeword width 12, which means the size of the codeword does not change and they will make the compression ratio smaller when compress the files do not the 12 codeword width. Also, when the original compression compresses the file that need the more bits, the 12 codeword width does not enough, which means the compression will be hampered later on. All in all, the original compression can be effective when compress the files that need 12 codeword width to compress. However, the main problem is that the original compression cannot be changed to improve efficiency when compress the varies. In other word, the original compression can be only effective on some specific file,and cannot be compress the general files effectively.

Compared to the Original Compression, the N-Compression, R-Compression Mac-Compress can change their codeword depend on the files, which means they have more Compression ratio on the general files.The difference of N-Compression and R-Compression is that R-Compression can reset the dictionary for the larger file, which means they can compress the large and various data patterner effectively since they can add new pattern when the codebook meets maximum. However, the compression ratio is relatively low during process of building up new pattern. N-Compression will not change the codeword, and just use and maintain maximum of the codeword to compress the file. However, the problem is that when the file has different pattern, the codebook cannot add the new pattern, and the compression ratio is low. Mac-compression is the most effective compression.

The table shows the compression ratio of different files. In the all.tar file case, the most effective compression is R-Compression except the Mac-compression. Since the all.tar file is a large and has various data pattern, which is the compress version of all the file so this file has various data pattern, the R-compression is the most effective way to compress the large and various data pattern.

In the bmps.tar case, the bmps.tar file consist of all the pictures. In the wacky.bmp ,winnt256.bmp. Many picture contain repeated data pattern, so the most effective way to compress that picture file is the N-compression, since N-compression can have better compression ratio on the repeated data pattern.

In the assig2.doc, code.txt,code2.txt medium.txt large.txt texts.tar case, the N-compression ratio and R-compression ratio are almost same, which means both compression have same compression ability on the text. The reason of that I think is because the relative repeated data pattern of the texts. Since most test file have limits number character, and sign, which means such data pattern does not need the many codeword. Therefore, the compression ratios are almost same in the text file case.

In the frosty.jpg,gone_gishing.bmp,lego-big.gif case, most compression cannot compress the file, sometime the compression even make the file become larger. The reason is that these files are all compressed and picture file. They already become the most compression file, which mean they cannot be compressed any more. Therefore, some compression cannot change the size of the file, some compression make the file even larger. Because the compression need the codebook and codeword to compress the file, those data add more size to the compressed file.

The wacky.bmp has the highest compression ratio. I think the reason is because most the area of the picture is white, which means there many repeated data pattern. N-compresson and R-compression are really good at compressing repeated data pattern.

The worst compression ratio is the gone_fishing.bmp.Z. I think the reason is that this file is already a compressed file, and it cannot be compressed anymore. Therefore, the compression cannot work, even make the file larger than the original.








