package de.redgames.bloodskulls.skull;

import de.redgames.bloodskulls.util.LanguageUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.json.simple.JSONObject;

import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

@SuppressWarnings("unused")
public enum SkullType {
    // Craftbook skulls
    BAT("d5b936bc31359cbe77f2f1f0646c1f168d5bedecac3cb46ff31ceaaff49d16b8", "b59fc8a4-afc2-49f4-9814-da00b1671ffe", "bozzobrain", "coolwhip101"),
    BLAZE("d06e342f90ec538aaa1552b224f264a040840902e126d91ece6139aa5b3c7cc3", "8ad442c6-c7e1-4338-8393-3958203573c9", "MHF_Blaze", "Blaze_Head"),
    CAVE_SPIDER("77b07063a6874fa3e22548e02062bd733c25885929809624180aebb851557f6a", "e7a4527b-4c27-4ac3-9495-02e1fe7ded12", "MHF_CaveSpider"),
    CHICKEN("916b8e98389c541bb36453850bcbd1f7bc5a57da62dcc505060409737ec5b72a", "c0a48055-2697-4ebb-81c4-c575c1806979", "MHF_Chicken", "scraftbrothers1"),
    COW("d0e4e6fbf5f3dcf94422a1f319448f152369d179dbfbcdf00e5bfe8495fa977", "1b898508-f1b9-4080-a13e-d1876a7a65d4", "MHF_Cow", "VerifiedBernard", "CarlosTheCow"),
    DONKEY("63a976c047f412ebc5cb197131ebef30c004c0faf49d8dd4105fca1207edaff3", "e108021c-9070-4c4c-bd3d-db16c4b204c2", "Donkey"),
    ELDER_GUARDIAN("4c75632d8b54c64d5ea27ac75688836934e8ce2fd9f2643388d005cf15d79870", "f97140d1-21d5-4fcd-a64a-a98f3d4e6793", "ElderGuardian"),
    ENDERMAN("1b09a3752510e914b0bdc9096b392bb359f7a8e8a9566a02e7f66faff8d6f89e", "9fe10265-5fcc-44e9-8e12-fad87e54fc71", "MHF_Enderman", "Violit"),
    ENDERMITE("1730127e3ac7677122422df0028d9e7368bd157738c8c3cddecc502e896be01c", "b718e36b-8169-44ef-be75-a63b51d84cc1", "MHF_Endermite"),
//    ENDER_DRAGON("e5253b7ac5994e24c783a6673332575e9e2ca9a36b06bd55e9a92c6355d3d2a0", "bc4066c1-8276-4524-b1d4-ff86b3f3f79b", "MHF_EnderDragon"),
    EVOKER("d954135dc82213978db478778ae1213591b93d228d36dd54f1ea1da48e7cba6", "75694f27-3b70-4ffc-bb31-3799b5232c10", "MFH_Evoker"),
    GHAST("4a4e42eb15a08813a6a6f61f10aa288019fa0fae106a2953ddb46f77ee2d77f", "671c3246-f82c-4bf1-a064-f6c3dbebca7b", "MHF_Ghast", "_QuBra_"),
    GUARDIAN("270855833fa59d19373d6e9bb5d8a860e3270fa7f36ba5ea93b5a2bb2399a576", "52b6bb31-347d-4321-911d-af94a240458b", "MHF_Guardian", "Guardian"),
    HORSE("f0bdc050488a50828ecf7a14e5d334eb0679f1f4a7f1cb8904b0323a28186422", "6db5919c-5f21-4eed-b44e-84d5b86fda35", "gavertoso"),
    IRON_GOLEM("1c6cd7202c34e78f3073090349f7d973b288af5e5b7334dd249010b3f27078f9", "4ca2405c-0aea-4b54-a211-dd39bce75945", "MHF_Golem", "zippie007"),
    MAGMA_CUBE("d90d61e8ce9511a0a2b5ea2742cb1ef36131380ed4129e1b163ce8ff000de8ea", "7a22a874-374f-4c17-9ba1-ba79c3b20b2f", "MHF_LavaSlime"),
    MOOSHROOM("123cfc5582454fcf9906f841fda2cc6ae896cf455821c4ada1998de70877cc86", "67585e01-40fa-4c58-93eb-d00b20993f28", "MHF_MushroomCow", "Mooshroom_Stew"),
    OCELOT("118b6b79783368dfe0042985110da366f9c788b45097a3ea6d0d9a753e9f42c6", "331c63d5-7bc7-4709-ad86-24c4f06ddde0", "MHF_Ocelot", "scraftbrothers3"),
    PARROT("b67a984cdd5391c67ebbf33316abfec18ca5be02335dcb8a953f2ac5902460ab", "cab2b03c-8378-4c66-bfd5-cd45d89b0073", "MHF_Parrot"),
    PIG("a562a37b871f964bfc3e1311ea672aaa03984a5dc472154a34dc25af157e382b", "ca1be405-0d59-4728-aca3-f89fe5da776e", "MHF_Pig", "XlexerX"),
    ZOMBIE_PIGMAN("916d167c5744ed14ebc02f447f32614059362b7d2ecb808ff06165d2c343bef2", "d210cb9f-ebf1-4200-b1cd-65b867c443e6", "MHF_PigZombie", "ManBearPigZombie", "scraftbrothers5"),
    POLAR_BEAR("442123ac15effa1ba46462472871b88f1b09c1db467621376e2f71656d3fbc", "5e7e19f1-2393-4dab-955b-9114709d461d", "Polar_Bear", "ice_bear", "_DmacK_"),
    RABBIT("65fa1ec985f1de430ad28cd592337ee5ee261accd27b82162d178d8381ecadb2", "b2d0fad3-e62a-4ecd-8d04-10cf2440bf27", "MHF_Rabbit", "rabbit2077"),
    SHEEP("7ca38ccf417e99ca9d47eeb15a8a30edb1507aa52b678c220c717c474aa6fe3e", "cbc900ca-42bd-4d2b-920f-d3526a68ed3f", "MHF_Sheep", "SGT_KICYORASS", "Eagle_Peak"),
    SHULKER("5080da9d2f89c3c408217b0c221309854b3d3510d238eb06d35fc1a541bb40d8", "365797a7-8e86-4f87-b3d3-371598441a83", "MHF_Shulker"),
//    SILVERFISH("544e98deb5bb3007d9022f59858066ba97a1b002641ccba8382a98c0c4077f89", "b5a03a12-1c49-405a-8a52-67a75e4c5eed", Xzomag", "AlexVMiner"),
    SLIME("86c27b013f1bf3344869e81e5c610027bc45ec5b79514fdc96e01df1b7e3a387", "29a57cea-94fb-46a1-84e1-d5e2d0a12087", "MHF_Slime", "HappyHappyMan"),
    SNOW_GOLEM("f19c0bf9cecae9c7d3ee05125b0a92e5ba48e90680e5997053cb5c07d28a0d9c", "524e1883-7936-4dc0-9509-a1a86bd63830", "MHF_SnowGolem", "Koebasti", "scraftbrothers2"),
    SPIDER("f61a49541a836aa8f4f76e0d4cb2ff04888c62f9411ea10cbacf1f2a54424240", "ee597f92-6798-46eb-9cda-d4fc20f796a6", "MHF_Spider", "Kelevra_V"),
    STRAY("2e9369029c0353ccf6384b4c420bf5b03920a545f2fc6d8ccc95f8272ef6a1e3", "9e04c7c7-37e1-49d9-a2b5-f372c13f4a48", "MHF_Stray"),
    SQUID("5e89101d5cc74aa458021a060f6289a51a35a7d34d8caddfc3cdf3b2c9a071a", "5a59c72c-9088-45f0-9308-7871c278a228", "MHF_Squid", "squidette8"),
    WITCH("20a46799b6ce4b7e29a8def9f54f30cc7025e96321625f2ab40a9d70b8436b21", "e8e19f52-1d9f-4166-83c1-be996a48ca0b", "MHF_Witch", "scrafbrothers4"),
    WITHER("ea15d05d0a868968c668b5d86c4083053ebe9fdea9ce4aa1315035d3ba8843d", "006df577-b525-4795-bbe8-12e671270b4d", "MHF_Wither"),
    WOLF("696a537a813a1c17ab4c90d212b80d03b1baf1e3cf172f5ffe02afd0532fccef", "8a5fd93b-2713-4aa8-9502-206f2c5cced2", "MHF_Wolf", "Budwolf"),
    VEX("46cb494e7e4dcb3e7b3286cda9097b38cc90fc7cfad58368183b0b86b0e6b47f", "c318d5d2-dda9-4014-96f8-e7dd0d42acbd", "MHF_Vex"),
    VILLAGER("b4bd832813ac38e68648938d7a32f6ba29801aaf317404367f214b78b4d4754c", "31760692-f82c-4817-8120-6f70fa59aa6e", "MHF_Villager", "Villager", "Kuvase", "scraftbrothers9"),

    // Own skulls
    PHANTOM("7e95153ec23284b283f00d19d29756f244313a061b70ac03b97d236ee57bd982", "55aa6b61-fe9e-4812-bb10-3022220ff52a"),
    PUFFERFISH("17152876bc3a96dd2a2299245edb3beef647c8a56ac8853a687c3e7b5d8bb", "42c88c1a-7711-4dd2-817e-7679fb1a3ca7"),
    COD("7892d7dd6aadf35f86da27fb63da4edda211df96d2829f691462a4fb1cab0", "27f9a3f3-4548-4c3e-b401-1bd0872a8189"),
    SALMON("8aeb21a25e46806ce8537fbd6668281cf176ceafe95af90e94a5fd84924878", "3b49c4e1-0169-4314-a129-33bfd5e39a15"),
    TROPICAL_FISH("4b953e4d3dfa1c622f30c4482d9a726ea12592134f1e3bc79e0c0c5d84c36", "44c7f727-cfc3-4930-bf47-d2414e9a2c90"),
    TURTLE("0a4050e7aacc4539202658fdc339dd182d7e322f9fbcc4d5f99b5718a", "39ddcbe4-1edb-4c14-b6ee-8432ec430e24"),
    DROWNED("c84df79c49104b198cdad6d99fd0d0bcf1531c92d4ab6269e40b7d3cbbb8e98c", "5588f385-9ae8-429c-9038-88e936cd5c59"),
    LLAMA("c2b1ecff77ffe3b503c30a548eb23a1a08fa26fd67cdff389855d74921368", "c52c20ae-4120-41b3-a9c8-8fa88eb0c9b5"),
    DOLPHIN("8e9688b950d880b55b7aa2cfcd76e5a0fa94aac6d16f78e833f7443ea29fed3", "1d990fd5-50db-462d-a177-042b4670494a"),
    HUSK("d674c63c8db5f4ca628d69a3b1f8a36e29d8fd775e1a6bdb6cabb4be4db121", "11cbb3b7-81eb-45fa-987b-3b82d0ff7d4c"),
    ILLUSIONER("6a014477f5a3e6696fa51d9fe9e6eb52fb61ed22e2a374870c571641edbf0d39", "7357875a-8f66-475b-9d91-00462964a8fb"),
    MULE("a0486a742e7dda0bae61ce2f55fa13527f1c3b334c57c034bb4cf132fb5f5f", "b87ca4e1-98c5-4ac0-a63f-9b10a2d58f23"),
    SKELETON_HORSE("47effce35132c86ff72bcae77dfbb1d22587e94df3cbc2570ed17cf8973a", "db2b2ece-7820-4875-b772-244c6d029845"),
    VINDICATOR("4f6fb89d1c631bd7e79fe185ba1a6705425f5c31a5ff626521e395d4a6f7e2", "25fa8eb0-6854-47c5-b844-36a487501954"),
    ZOMBIE_HORSE("d22950f2d3efddb18de86f8f55ac518dce73f12a6e0f8636d551d8eb480ceec", "1f36c391-057b-472c-893c-f32d372acddb"),
    ZOMBIE_VILLAGER("e5e08a8776c1764c3fe6a6ddd412dfcb87f41331dad479ac96c21df4bf3ac89c", "802f7419-28e9-495b-826c-b4187ff1d5a1"),
    // Own skulls | 1.14
    TRADER_LLAMA("8424780b3c5c5351cf49fb5bf41fcb289491df6c430683c84d7846188db4f84d", "7e548e7f-cbdf-4a93-a2e1-ad0fd10084a9"),
    WANDERING_TRADER("5f1379a82290d7abe1efaabbc70710ff2ec02dd34ade386bc00c930c461cf932", "d1733f58-5ed7-4070-86d1-5d99b67fd8df"),
    FOX("d8954a42e69e0881ae6d24d4281459c144a0d5a968aed35d6d3d73a3c65d26a", "a87851e3-732e-4310-9f83-d84287789959"),
    CAT("d5b3f8ca4b3a555ccb3d194449808b4c9d783327197800d4d65974cc685af2ea", "3b8a0498-62d8-46d4-87aa-300eda5ba64d"),
    PILLAGER("4aee6bb37cbfc92b0d86db5ada4790c64ff4468d68b84942fde04405e8ef5333", "77f23247-7c70-408c-bd42-10b0135f96d6"),
    RAVAGER("cd20bf52ec390a0799299184fc678bf84cf732bb1bd78fd1c4b441858f0235a8", "87c17772-d769-4732-a066-d3c3be3ada57"),
    // Own skulls | 1.15
    BEE("59ac16f296b461d05ea0785d477033e527358b4f30c266aa02f020157ffca736", "fb5f97f4-23b9-4a2a-8e86-5fa9a3dd07b0"),

    // Player skull
    PLAYER("00000052-6f64-6e65-7957-617348657265"),
    ZOMBIE(),
    CREEPER(),
    SKELETON(),
    ENDER_DRAGON(),
    WITHER_SKELETON();

    private static HashMap<String, SkullType> craftbookNameMap = new HashMap<>();
    private static HashMap<String, SkullType> uuidMap = new HashMap<>();
    private static final String TEXTURE_URL = "http://textures.minecraft.net/texture/";

    private String defaultTexture;
    private String uuid;
    private String[] craftbookNames;

    SkullType(String defaultTexture, String uuid, String... craftbookNames) {
        this.defaultTexture = defaultTexture;
        this.uuid = uuid;
        this.craftbookNames = craftbookNames;
    }

    SkullType(String uuid) {
        this.uuid = uuid;
    }

    SkullType() {}

    public String getTextureValue() {
        if(defaultTexture == null) throw new IllegalAccessError("Selected SkullType has no default texture!");
        return getTextureValue(TEXTURE_URL + defaultTexture);
    }

    @SuppressWarnings("unchecked")
    public String getTextureValue(String textureUrl) {
        JSONObject skin = new JSONObject();
        JSONObject textures = new JSONObject();
        JSONObject value = new JSONObject();
        skin.put("url", textureUrl);
        textures.put("SKIN", skin);
        value.put("textures", textures);
        return Base64.getEncoder().encodeToString(value.toJSONString().getBytes());
    }

    public UUID getUUID() {
        return UUID.fromString(uuid);
    }

    public String getName() {
        return LanguageUtil.getTranslation("entity.minecraft." + name().toLowerCase());
    }

    public String getDisplayName() {
        if (this == SkullType.PLAYER) return null;
        return ChatColor.YELLOW + getName() + " Head";
    }

    public static SkullType getSkullTypeByUUID(String uuid) {
        return uuidMap.getOrDefault(uuid, null);
    }
    public static SkullType getSkullTypeByEntityType(EntityType entityType) {
        try {
            switch (entityType) {
                case MUSHROOM_COW:
                    return MOOSHROOM;
                case PIG_ZOMBIE:
                    return ZOMBIE_PIGMAN;
                case SNOWMAN:
                    return SNOW_GOLEM;
                default:
                    return SkullType.valueOf(entityType.name());
            }
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    public static SkullType getSkullTypeByCraftbookName(String name) {
        return craftbookNameMap.getOrDefault(name, null);
    }

    static {
        for (SkullType skullType : SkullType.values()) {
            if (skullType.craftbookNames == null) continue;
            for (String craftbookName : skullType.craftbookNames) {
                craftbookNameMap.put(craftbookName, skullType);
            }
            uuidMap.put(skullType.uuid, skullType);
        }
    }
}
