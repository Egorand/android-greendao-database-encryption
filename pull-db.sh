#
# pull-db
# Inspect the database from your device
# Cedric Beust
#
 
PKG=me.egorand.mysecrets
DB=secrets.db
 
adb shell "run-as $PKG chmod 755 /data/data/$PKG/databases"
adb shell "run-as $PKG chmod 666 /data/data/$PKG/databases/$DB"
adb shell "rm /sdcard/$DB"
adb shell "cp /data/data/$PKG/databases/$DB /sdcard/$DB"

mkdir tmp
rm -f tmp/${DB}
adb pull /sdcard/${DB} tmp/${DB}
 
open /Applications/SQLite\ Browser.app tmp/${DB}
