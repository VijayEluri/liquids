#!/bin/bash
sudo java -cp "liquids.jar:lib/gluegen-rt.jar:lib/*" -Djava.library.path=lib/linux_x64 ch.hsr.ifs.liquids.Liquids
