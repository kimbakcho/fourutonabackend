package com.wing.forutona.AuthDao;

import com.wing.forutona.AuthDto.PhoneauthtableCustom;

public interface PhoneauthtableCustomMapper extends PhoneauthtableMapper {
    int CreateRemoveEvent(PhoneauthtableCustom record);
    int DropRemoveEvent(PhoneauthtableCustom record);
}
