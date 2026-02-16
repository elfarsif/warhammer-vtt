package org.example.model.datasheet;

import java.util.List;

public record DataSheet(Picture picture, Stats stats, List<RangedWeapon> rangedWeapons, List<MeleeWeapon> meleeWeapons, List<Ability> abilities) {}
