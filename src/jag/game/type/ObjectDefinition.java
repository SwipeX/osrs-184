package jag.game.type;

import jag.commons.collection.DoublyLinkedNode;
import jag.commons.collection.IterableNodeTable;
import jag.commons.collection.Node;
import jag.commons.collection.ReferenceCache;
import jag.game.Vars;
import jag.game.scene.entity.Entity;
import jag.game.scene.entity.Model;
import jag.game.scene.entity.UnlitModel;
import jag.js5.ReferenceTable;
import jag.opcode.Buffer;

public class ObjectDefinition extends DoublyLinkedNode {
    public static final ReferenceCache<ObjectDefinition> cache = new ReferenceCache<>(4096);
    public static final ReferenceCache<Model> models = new ReferenceCache<>(30);
    public static final ReferenceCache<DoublyLinkedNode> aReferenceCache1513 = new ReferenceCache<>(30);
    public static final ReferenceCache<UnlitModel> rawmodels = new ReferenceCache<>(500);
    public static final UnlitModel[] anUnlitModelArray1512 = new UnlitModel[4];
    public static boolean aBoolean792 = false;
    public static ReferenceTable aReferenceTable1515;
    public static ReferenceTable aReferenceTable697;

    public int mapDoorFlag;
    public int[] transformIds;
    public String name;
    public int ambientSoundId;
    public int itemSupport;
    public int id;
    public int[] soundEffects;
    public int sizeX;
    public int anInt791;
    public int mapFunction;
    public int sizeY;
    public boolean impenetrable;
    public int anInt1344;
    public int anInt1508;
    public boolean projectileClipped;
    public String[] actions;
    public int anInt1510;
    public int animation;
    public int anInt1369;
    public boolean clipped;
    public int mapSceneId;
    public boolean aBoolean1507;
    public IterableNodeTable<? super Node> properties;
    public int varpbitIndex;
    public int[] anIntArray690;
    public int[] modelIds;
    public int varpIndex;
    public boolean rotated;
    public boolean aBoolean1511;
    public int clipType;
    public int ambient;
    public int contrast;
    public boolean solid;
    public int scaleX;
    public int scaleZ;
    public int translateX;
    public short[] colors;
    public short[] textures;
    public short[] newColors;
    public int scaleY;
    public short[] newTextures;
    public int translateY;
    public int translateZ;

    public ObjectDefinition() {
        name = "null";
        sizeX = 1;
        sizeY = 1;
        anInt791 = 2;
        impenetrable = true;
        mapDoorFlag = -1;
        clipType = -1;
        aBoolean1511 = false;
        projectileClipped = false;
        animation = -1;
        anInt1369 = 16;
        ambient = 0;
        contrast = 0;
        actions = new String[5];
        mapFunction = -1;
        mapSceneId = -1;
        rotated = false;
        clipped = true;
        scaleX = 128;
        scaleZ = 128;
        scaleY = 128;
        translateX = 0;
        translateY = 0;
        translateZ = 0;
        aBoolean1507 = false;
        solid = false;
        itemSupport = -1;
        varpbitIndex = -1;
        varpIndex = -1;
        ambientSoundId = -1;
        anInt1344 = 0;
        anInt1508 = 0;
        anInt1510 = 0;
    }

    public static ObjectDefinition get(int var0) {
        ObjectDefinition var2 = cache.get(var0);
        if (var2 != null) {
            return var2;
        }
        byte[] var3 = aReferenceTable697.unpack(6, var0);
        var2 = new ObjectDefinition();
        var2.id = var0;
        if (var3 != null) {
            var2.method260(new Buffer(var3));
        }

        var2.method254();
        if (var2.solid) {
            var2.anInt791 = 0;
            var2.impenetrable = false;
        }

        cache.put(var2, var0);
        return var2;
    }

    public final ObjectDefinition transform() {
        int var1 = -1;
        if (varpbitIndex != -1) {
            var1 = Varbit.get(varpbitIndex);
        } else if (varpIndex != -1) {
            var1 = Vars.values[varpIndex];
        }

        int var2;
        if (var1 >= 0 && var1 < transformIds.length - 1) {
            var2 = transformIds[var1];
        } else {
            var2 = transformIds[transformIds.length - 1];
        }

        return var2 != -1 ? get(var2) : null;
    }

    public void method259(Buffer var1, int var2) {
        int var3;
        int var4;
        if (var2 == 1) {
            var3 = var1.g1();
            if (var3 > 0) {
                if (anIntArray690 != null && !aBoolean792) {
                    var1.pos += 3 * var3;
                } else {
                    modelIds = new int[var3];
                    anIntArray690 = new int[var3];

                    for (var4 = 0; var4 < var3; ++var4) {
                        anIntArray690[var4] = var1.g2();
                        modelIds[var4] = var1.g1();
                    }
                }
            }
        } else if (var2 == 2) {
            name = var1.gstr();
        } else if (var2 == 5) {
            var3 = var1.g1();
            if (var3 > 0) {
                if (anIntArray690 != null && !aBoolean792) {
                    var1.pos += var3 * 2;
                } else {
                    modelIds = null;
                    anIntArray690 = new int[var3];

                    for (var4 = 0; var4 < var3; ++var4) {
                        anIntArray690[var4] = var1.g2();
                    }
                }
            }
        } else if (var2 == 14) {
            sizeX = var1.g1();
        } else if (var2 == 15) {
            sizeY = var1.g1();
        } else if (var2 == 17) {
            anInt791 = 0;
            impenetrable = false;
        } else if (var2 == 18) {
            impenetrable = false;
        } else if (var2 == 19) {
            mapDoorFlag = var1.g1();
        } else if (var2 == 21) {
            clipType = 0;
        } else if (var2 == 22) {
            aBoolean1511 = true;
        } else if (var2 == 23) {
            projectileClipped = true;
        } else if (var2 == 24) {
            animation = var1.g2();
            if (animation == 65535) {
                animation = -1;
            }
        } else if (var2 == 27) {
            anInt791 = 1;
        } else if (var2 == 28) {
            anInt1369 = var1.g1();
        } else if (var2 == 29) {
            ambient = var1.g1b();
        } else if (var2 == 39) {
            contrast = var1.g1b();
        } else if (var2 >= 30 && var2 < 35) {
            actions[var2 - 30] = var1.gstr();
            if (actions[var2 - 30].equalsIgnoreCase("Hidden")) {
                actions[var2 - 30] = null;
            }
        } else if (var2 == 40) {
            var3 = var1.g1();
            colors = new short[var3];
            newColors = new short[var3];

            for (var4 = 0; var4 < var3; ++var4) {
                colors[var4] = (short) var1.g2();
                newColors[var4] = (short) var1.g2();
            }
        } else if (var2 == 41) {
            var3 = var1.g1();
            textures = new short[var3];
            newTextures = new short[var3];

            for (var4 = 0; var4 < var3; ++var4) {
                textures[var4] = (short) var1.g2();
                newTextures[var4] = (short) var1.g2();
            }
        } else if (var2 == 62) {
            rotated = true;
        } else if (var2 == 64) {
            clipped = false;
        } else if (var2 == 65) {
            scaleX = var1.g2();
        } else if (var2 == 66) {
            scaleZ = var1.g2();
        } else if (var2 == 67) {
            scaleY = var1.g2();
        } else if (var2 == 68) {
            mapSceneId = var1.g2();
        } else if (var2 == 69) {
            var1.g1();
        } else if (var2 == 70) {
            translateX = var1.g2b();
        } else if (var2 == 71) {
            translateY = var1.g2b();
        } else if (var2 == 72) {
            translateZ = var1.g2b();
        } else if (var2 == 73) {
            aBoolean1507 = true;
        } else if (var2 == 74) {
            solid = true;
        } else if (var2 == 75) {
            itemSupport = var1.g1();
        } else if (var2 != 77 && var2 != 92) {
            if (var2 == 78) {
                ambientSoundId = var1.g2();
                anInt1344 = var1.g1();
            } else if (var2 == 79) {
                anInt1508 = var1.g2();
                anInt1510 = var1.g2();
                anInt1344 = var1.g1();
                var3 = var1.g1();
                soundEffects = new int[var3];

                for (var4 = 0; var4 < var3; ++var4) {
                    soundEffects[var4] = var1.g2();
                }
            } else if (var2 == 81) {
                clipType = var1.g1();
            } else if (var2 == 82) {
                mapFunction = var1.g2();
            } else if (var2 == 249) {
                properties = IterableNodeTable.decode(var1, properties);
            }
        } else {
            varpbitIndex = var1.g2();
            if (varpbitIndex == 65535) {
                varpbitIndex = -1;
            }

            varpIndex = var1.g2();
            if (varpIndex == 65535) {
                varpIndex = -1;
            }

            var3 = -1;
            if (var2 == 92) {
                var3 = var1.g2();
                if (var3 == 65535) {
                    var3 = -1;
                }
            }

            var4 = var1.g1();
            transformIds = new int[var4 + 2];

            for (int var5 = 0; var5 <= var4; ++var5) {
                transformIds[var5] = var1.g2();
                if (transformIds[var5] == 65535) {
                    transformIds[var5] = -1;
                }
            }

            transformIds[var4 + 1] = var3;
        }

    }

    public final UnlitModel method1102(int var1, int var2) {
        UnlitModel var3 = null;
        boolean var4;
        int var5;
        int var7;
        if (modelIds == null) {
            if (var1 != 10) {
                return null;
            }

            if (anIntArray690 == null) {
                return null;
            }

            var4 = rotated;
            if (var1 == 2 && var2 > 3) {
                var4 = !var4;
            }

            var5 = anIntArray690.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                var7 = anIntArray690[var6];
                if (var4) {
                    var7 += 65536;
                }

                var3 = rawmodels.get(var7);
                if (var3 == null) {
                    var3 = UnlitModel.method982(aReferenceTable1515, var7 & 65535, 0);
                    if (var3 == null) {
                        return null;
                    }

                    if (var4) {
                        var3.method981();
                    }

                    rawmodels.put(var3, var7);
                }

                if (var5 > 1) {
                    anUnlitModelArray1512[var6] = var3;
                }
            }

            if (var5 > 1) {
                var3 = new UnlitModel(anUnlitModelArray1512, var5);
            }
        } else {
            int var9 = -1;

            for (var5 = 0; var5 < modelIds.length; ++var5) {
                if (modelIds[var5] == var1) {
                    var9 = var5;
                    break;
                }
            }

            if (var9 == -1) {
                return null;
            }

            var5 = anIntArray690[var9];
            boolean var10 = rotated ^ var2 > 3;
            if (var10) {
                var5 += 65536;
            }

            var3 = rawmodels.get(var5);
            if (var3 == null) {
                var3 = UnlitModel.method982(aReferenceTable1515, var5 & 65535, 0);
                if (var3 == null) {
                    return null;
                }

                if (var10) {
                    var3.method981();
                }

                rawmodels.put(var3, var5);
            }
        }

        var4 = scaleX != 128 || scaleZ != 128 || scaleY != 128;

        boolean var11;
        var11 = translateX != 0 || translateY != 0 || translateZ != 0;

        UnlitModel var8 = new UnlitModel(var3, var2 == 0 && !var4 && !var11, colors == null, null == textures);
        if (var1 == 4 && var2 > 3) {
            var8.method302(256);
            var8.method976(45, 0, -45);
        }

        var2 &= 3;
        if (var2 == 1) {
            var8.method886();
        } else if (var2 == 2) {
            var8.method972();
        } else if (var2 == 3) {
            var8.method969();
        }

        if (colors != null) {
            for (var7 = 0; var7 < colors.length; ++var7) {
                var8.texturize(colors[var7], newColors[var7]);
            }
        }

        if (textures != null) {
            for (var7 = 0; var7 < textures.length; ++var7) {
                var8.colorize(textures[var7], newTextures[var7]);
            }
        }

        if (var4) {
            var8.method764(scaleX, scaleZ, scaleY);
        }

        if (var11) {
            var8.method976(translateX, translateY, translateZ);
        }

        return var8;
    }

    public void method254() {
        if (mapDoorFlag == -1) {
            mapDoorFlag = 0;
            if (anIntArray690 != null && (modelIds == null || modelIds[0] == 10)) {
                mapDoorFlag = 1;
            }

            for (int var1 = 0; var1 < 5; ++var1) {
                if (actions[var1] != null) {
                    mapDoorFlag = 1;
                    break;
                }
            }
        }

        if (itemSupport == -1) {
            itemSupport = anInt791 != 0 ? 1 : 0;
        }

    }

    public void method260(Buffer var1) {
        while (true) {
            int var2 = var1.g1();
            if (var2 == 0) {
                return;
            }

            method259(var1, var2);
        }
    }

    public final Model method1107(int var1, int var2, int[][] var3, int var4, int var5, int var6, AnimationSequence var7, int var8) {
        long var9;
        if (modelIds == null) {
            var9 = var2 + (id << 10);
        } else {
            var9 = var2 + (var1 << 3) + (id << 10);
        }

        Model var11 = models.get(var9);
        if (var11 == null) {
            UnlitModel var12 = method1102(var1, var2);
            if (var12 == null) {
                return null;
            }

            var11 = var12.light(ambient + 64, contrast * 25 + 768, -50, -10, -50);
            models.put(var11, var9);
        }

        if (var7 == null && clipType == -1) {
            return var11;
        }
        if (var7 != null) {
            var11 = var7.method877(var11, var8, var2);
        } else {
            var11 = var11.method1291(true);
        }

        if (clipType >= 0) {
            var11 = var11.contourGround(var3, var4, var5, var6, false, clipType);
        }

        return var11;
    }

    public final Model method1103(int var1, int var2, int[][] var3, int var4, int var5, int var6) {
        long var7;
        if (modelIds == null) {
            var7 = var2 + (id << 10);
        } else {
            var7 = var2 + (var1 << 3) + (id << 10);
        }

        Model var9 = models.get(var7);
        if (var9 == null) {
            UnlitModel var10 = method1102(var1, var2);
            if (var10 == null) {
                return null;
            }

            var9 = var10.light(ambient + 64, contrast * 25 + 768, -50, -10, -50);
            models.put(var9, var7);
        }

        if (clipType >= 0) {
            var9 = var9.contourGround(var3, var4, var5, var6, true, clipType);
        }

        return var9;
    }

    public final boolean method882() {
        if (anIntArray690 == null) {
            return true;
        }
        boolean var1 = true;

        for (int anAnIntArray690 : anIntArray690) {
            var1 &= aReferenceTable1515.load(anAnIntArray690 & 65535, 0);
        }

        return var1;
    }

    public final boolean method1106(int var1) {
        if (modelIds != null) {
            for (int var4 = 0; var4 < modelIds.length; ++var4) {
                if (modelIds[var4] == var1) {
                    return aReferenceTable1515.load(anIntArray690[var4] & 65535, 0);
                }
            }

            return true;
        }
        if (anIntArray690 == null) {
            return true;
        }
        if (var1 != 10) {
            return true;
        }
        boolean var2 = true;

        for (int anAnIntArray690 : anIntArray690) {
            var2 &= aReferenceTable1515.load(anAnIntArray690 & 65535, 0);
        }

        return var2;
    }

    public int method1100(int var1, int var2) {
        return IterableNodeTable.getIntParameter(properties, var1, var2);
    }

    public String method1104(int var1, String var2) {
        return IterableNodeTable.getStringParameter(properties, var1, var2);
    }

    public boolean method1099() {
        if (transformIds == null) {
            return ambientSoundId != -1 || soundEffects != null;
        }
        for (int transformId : transformIds) {
            if (transformId != -1) {
                ObjectDefinition var2 = get(transformId);
                if (var2.ambientSoundId != -1 || var2.soundEffects != null) {
                    return true;
                }
            }
        }

        return false;
    }

    public final Entity method1105(int var1, int var2, int[][] var3, int var4, int var5, int var6) {
        long var7;
        if (modelIds == null) {
            var7 = var2 + (id << 10);
        } else {
            var7 = var2 + (var1 << 3) + (id << 10);
        }

        DoublyLinkedNode node = aReferenceCache1513.get(var7);
        if (node == null) {
            UnlitModel var10 = method1102(var1, var2);
            if (var10 == null) {
                return null;
            }

            if (!aBoolean1511) {
                node = var10.light(ambient + 64, contrast * 25 + 768, -50, -10, -50);
            } else {
                var10.ambience = (short) (ambient + 64);
                var10.contrast = (short) (contrast * 25 + 768);
                var10.method593();
                node = var10;
            }

            aReferenceCache1513.put(node, var7);
        }

        if (aBoolean1511) {
            node = ((UnlitModel) node).method978();
        }

        if (clipType >= 0) {
            if (node instanceof Model) {
                node = ((Model) node).contourGround(var3, var4, var5, var6, true, clipType);
            } else if (node instanceof UnlitModel) {
                node = ((UnlitModel) node).method977(var3, var4, var5, var6, clipType);
            }
        }

        return (Entity) node;
    }
}
