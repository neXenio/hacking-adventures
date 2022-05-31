package com.nexenio.util

object PasswordHashes {
    // TODO: crack these

    // 100MB wordlist available at https://github.com/brannondorsey/naive-hashcat/releases/download/data/rockyou.txt
    // ~15 million words, helpful for challenges 2A and 2B

    // SHA-256 hashes taken from https://id0-rsa.pub/problem/25/
    // format: $<<salt>>$<<hash>>
    // notes: the salt is ascii encoded, the hash is base64 encoded
    const val CHALLENGE_2_A1 = "\$(y3]<+9zmi4|\$6Rup8P8oJnxK98aXa8HhGROLdvws9xmgawl7rsh2E5E="
    const val CHALLENGE_2_A2 = "\$b*.m,%~&<\"^6\$l93FR8Rq8a+YIUdcC2Kdake7/rlSU1zAr/9yAiRZVI0="
    const val CHALLENGE_2_A3 = "\$9bOv^Gu)oB&P\$EdEfD9X20gQi+sUYRvHyuoCMGq7DCeD/UJSSDmCvjZA="
    const val CHALLENGE_2_A4 = "\$kPD)T)=~1K{r\$BgOuh0tBaGKtcFscQvdwFBscgC+pYKW1qpFDDwTJRAA="
    const val CHALLENGE_2_A5 = "\$4.9.mHSbiQ]^\$by2hg2rG18QKk9pMqa/Fb9vnJ5/NEvR5qpg9SVdy3nM="
    const val CHALLENGE_2_A6 = "\${4[1m\"WqdR0s\$Vz+gAWYf/8PIKu7ILxaVFnDcNCzAcerci8caiCYgm2Y="
    const val CHALLENGE_2_A7 = "\$3ui!yKfT0[Si\$QZJcfHWh+OsdkgkrrZNp8ZkYlc3sWlT57PgC/YhmaRY="

    // MD5-crypt hashes taken from https://id0-rsa.pub/problem/38/
    // format: $1$<<salt>>$<<hash>>
    // notes: the $1$ indicates md5 crypt is used, the other possible value is apr1 which stands for apr_md5_crypt
    const val CHALLENGE_2_B1 = "\$1\$abadsalt\$0abdVS0D4YnJJ4b7l0RRr1"
    const val CHALLENGE_2_B2 = "\$1\$abadsalt\$p394aiqZnKUyrO5Rg9Tf01"
    const val CHALLENGE_2_B3 = "\$1\$abadsalt\$cJYsdaTkB9F9L9yH2Qjtd."
    const val CHALLENGE_2_B4 = "\$1\$abadsalt\$lFZDGpRdmOwRbu6HWuqjv0"
    const val CHALLENGE_2_B5 = "\$1\$abadsalt\$1AI/LbmumKa5e6dOxiVe11"
    const val CHALLENGE_2_B6 = "\$1\$abadsalt\$e2hAp/NXE.Uezx3ZOwA5L0"
    const val CHALLENGE_2_B7 = "\$1\$abadsalt\$Cua6x6Rgd8UUHn7Mnzibj."
    const val CHALLENGE_2_B8 = "\$1\$abadsalt\$7XBxlsUB3yXcL62wQpgjK/"
    const val CHALLENGE_2_B9 = "\$1\$abadsalt\$DnSSAXOSmaoAAhN4WKaU90"
    const val CHALLENGE_2_B10 = "\$1\$abadsalt\$Cua6x6Rgd8UUHn7Mnzibj."
    const val CHALLENGE_2_B11 = "\$1\$abadsalt\$7wLTt8frOzyxahbB9Lzdi."

    // word list won't help much here
    // ARGON2 hashes for short passwords of length 2-6 with A-Za-z0-9
    // format: $argon2i$v=19$m=65536,t=1,p=10$<<salt>>$<<hash>>
    // notes: the salt and hash are base64 encoded, 1 of the passwords has a special character, 2 consist only of numbers
    const val CHALLENGE_2_C1 = "\$argon2i\$v=19\$m=65536,t=1,p=10\$Nm9JRDE5aVJUWWp3elM0cw\$6JSf4tVeaCmwnfWXXzLk/tSB/pr7sKeez6GkVrSYg80"
    const val CHALLENGE_2_C2 = "\$argon2i\$v=19\$m=65536,t=1,p=10\$b2o0U1J6c1dyY1c5N0Jjbw\$X4ead4mz7Epls6HsX3/TIwplL97d6bd29V1MCo0rldE"
    const val CHALLENGE_2_C3 = "\$argon2i\$v=19\$m=65536,t=1,p=10\$dUJGZWRGUFhxODFTTTdZdg\$HMz/WMaamRkacitMybTpJ6slATrcLLyB6MKzzk8kr0c"
    const val CHALLENGE_2_C4 = "\$argon2i\$v=19\$m=65536,t=1,p=10\$NEtPOWZzMW9lOWNkZUgxQQ\$7Li/M2UQDrXUONG3afhnRuOUxgar9Xr6tarSiTVewaU"
    const val CHALLENGE_2_C5 = "\$argon2i\$v=19\$m=65536,t=1,p=10\$emtNbFExTFZRRVEwUmcweA\$O1PSDpE0pbr+JWrgg/qAqK/GVs9CiDl6Rp3SiVY3HIQ"
    const val CHALLENGE_2_C6 = "\$argon2i\$v=19\$m=65536,t=1,p=10\$YlhCNzVDUzl4TXhNODNrZw\$GorNvyopXFqZFM1BATzWOxWNqQDhzU9qT/IDbUYFymw"
    const val CHALLENGE_2_C7 = "\$argon2i\$v=19\$m=65536,t=1,p=10\$cldzN1hYbE1JaWcxdDl1eQ\$q5hbThQ2WCR2W0D+HzVeIH8RhbuIq/tzKu25DwxpD38"
    const val CHALLENGE_2_C8 = "\$argon2i\$v=19\$m=65536,t=1,p=10\$WWtvVkNtS2VJbzhvbkRhWA\$97T4CP4VTwNzpaUKvNOshcy1uJ1G01DE6pQt/n3Wbf8"
}
