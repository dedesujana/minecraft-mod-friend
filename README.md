# Friend Mod - Minecraft Neoforge

Sebuah mod Minecraft Java Neoforge yang memungkinkan Anda menambah teman, mengecek status online mereka, dan bergabung dengan mereka!

## Fitur

✅ **Tambah Teman (Add Friend)** - Gunakan command `/friend add <player>`
✅ **Hapus Teman (Remove Friend)** - Gunakan command `/friend remove <player>`
✅ **Lihat Teman Online (List Friends)** - Gunakan command `/friend list`
✅ **Cek Status Online (Check Status)** - Gunakan command `/friend status <player>`
✅ **GUI Friend List** - Tekan tombol `F` untuk membuka daftar teman

## Cara Build

### Requirements:
- Java Development Kit (JDK) 21+
- Gradle

### Build Steps:
```bash
# Clone repository
git clone https://github.com/dedesujana/minecraft-mod-friend.git
cd minecraft-mod-friend

# Build JAR
./gradlew build

# JAR file akan ada di: build/libs/friendmod-1.0.0.jar
```

## Installation

1. Download JAR file dari folder `build/libs/`
2. Masukkan ke folder `mods` di folder Minecraft Anda
3. Jalankan Minecraft dengan Neoforge Loader
4. Done! 🎉

## Commands

```
/friend add <player>     - Tambah teman
/friend remove <player>  - Hapus teman
/friend list             - Lihat teman yang online
/friend status <player>  - Cek status online teman
```

## Keyboard Shortcut

- Tekan `F` untuk membuka Friend List GUI

## Version

- **Minecraft**: 1.21.1
- **Neoforge**: 21.0.137-beta
- **Mod Version**: 1.0.0

## License

MIT License
