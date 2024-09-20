# BetterTree Plugin สำหรับ SpigotMC

ยินดีต้อนรับสู่ BetterTree! ปลั๊กอินนี้ช่วยให้การตัดต้นไม้ใน Minecraft ของผู้เล่นง่ายขึ้นมาก

## Main Features

- **ตัดต้นไม้ได้ง่าย:** ตัดต้นไม้ทั้งต้น ด้วยการกระทำเพียงครั้งเดียว
- **ปรับแต่งประสิทธิภาพ:** ออกแบบมาเพื่อทำงานได้อย่างราบรื่นโดยไม่มีการกระตุก
- **ใช้งานง่าย:** การติดตั้งและใช้งานที่สะดวกสบาย

## Installation

1. **ดาวน์โหลดปลั๊กอิน:** ไปที่ [releases](https://github.com/pichxyaponn/BetterTree/releases) เพื่อดาวน์โหลดเวอร์ชันล่าสุด
2. **เพิ่มไปยังโฟลเดอร์ Plugins:** วางไฟล์ `.jar` ที่ดาวน์โหลดเสร็จ ลงในโฟลเดอร์ `plugins` ของเซิร์ฟเวอร์
3. **รีสตาร์ทเซิร์ฟเวอร์:** รีสตาร์ทเซิร์ฟเวอร์เพื่อเปิดใช้งานปลั๊กอิน

## Usage

เมื่อเปิดใช้งานปลั๊กอิน ผู้เล่นแค่ทุบบล็อกของต้นไม้ และต้นไม้ทั้งหมดจะค่อยๆถูกลบออกโดยอัตโนมัติ (โดยเวลาคูลดาวน์ จะขึ้นอยู่กับ เครื่องมือที่ใช้ตัดต้นไม้)!

## Structure

ปลั๊กอินนี้ทำตามสถาปัตยกรรมที่ชัดเจน ประกอบด้วยส่วนประกอบดังนี้:

- **Model:** จัดการโครงสร้างข้อมูล (เช่น `BetterTreeModel`)
- **View:** จัดการส่วนติดต่อผู้ใช้ (เช่น `BetterTreeView`)
- **Controller:** ประสานงานการโต้ตอบระหว่าง Model และ View (เช่น `BetterTreeController`)

## Support

เรายินดีต้อนรับทุกการมีส่วนร่วม! ถ้ามีข้อเสนอแนะหรืออยากช่วยพัฒนาปลั๊กอิน ส่ง Pull Request ได้เลย

### Issues

ถ้ามีปัญหาหรือฟีเจอร์ที่อยากให้เพิ่ม แจ้งได้ที่ [หน้า Issues](https://github.com/pichxyaponn/BetterTree/issues)

## Acknowledgments

- ขอบคุณชุมชน SpigotMC สำหรับทรัพยากรที่มีอยู่ตลอดมา!
- ได้แรงบันดาลใจจากปลั๊กอินตัดต้นไม้ของพี่ Tackle4826 เพื่อให้การเล่นสะดวกสบายขึ้น

## License

Copyright © 2024 pichxyaponn

This project is licensed under the MIT License.

---

ขอให้สนุกกับการตัดต้นไม้ที่ง่ายขึ้นด้วย BetterTree! หากคุณมีคำถามหรือข้อเสนอแนะ สามารถติดต่อเราได้เลย Have Fun ! 🌳