# Experiment-6

<div dir="rtl">

## بخش اول

## بخش دوم

- دو مورد اعمال الگوی Facade: برای کلاس‌های CodeGenerator و Parser، دو کلاس Facade ساخته شد تا اولا پیجیدگی داخل این دو
  کلاس را پنهان کند و دوما یک رابط ساده برای کلاس‌هایی که میخواهند با آن‌ها کار کنند ارايه کند
- یک مورد State/Strategy یا استفاده از Polymorphism به جای شرط:
  در کلاس SymbolType یک پارامتر به Enum ها اضافه شد که VarType مربوط به هر مورد را ذخیره می‌کرد، و همین امر باعث شد که
  شرط‌های switch مختلف از بین بروند و تنها گرفتن مقدار varType انجام شود.
- یک مورد Separate Query From Modifier:
  برای این مورد در کلاس Memory، به جای توابع get قبلی، دو تابع جدید نوشته شده اند، که یکی وظیفه‌اش تنها get کردن و دیگری
  increment کردن است.
  نام توابع incrementTemp و incrementDataAddress است (دو مورد دیگر همان اسم قبلی را دارند.)
- یک مورد Self Encapsulated Field: در کلاس‌های Address، Parser و Rule این مورد انجام شده است (setter و getter)
- دو مورد مختلف غیر از بازآرایی‌های مطرح‌شده در موارد بالا: دو مورد تابع extract شده اند (doShiftAction و doReduceAction).
و مورد دوم اینکه مقدار scanner در کلاس Main استخراج شده و در یک متغیر قرار گرفته است.

## بخش سوم

</div>