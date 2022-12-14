package jag.opcode;

import jag.game.client;
import jag.game.scene.SceneGraph;
import jag.game.scene.entity.EntityUID;
import jag.game.scene.entity.PlayerEntity;
import jag.statics.Statics34;

public class GPI {
    public static final byte[] playerSkipFlags = new byte[2048];
    public static final byte[] aByteArray596 = new byte[2048];
    public static final Buffer[] buffers = new Buffer[2048];
    public static final int[] playerIndices = new int[2048];
    public static final int[] globalPlayerIndices = new int[2048];
    public static final int[] playerLocations = new int[2048];
    public static final int[] playerOrientations = new int[2048];
    public static final int[] playerTargetIndices = new int[2048];
    public static final int[] anIntArray588 = new int[2048];
    public static final Buffer chatBuffer = new Buffer(new byte[5000]);
    public static int playerCount = 0;
    public static int globalPlayerCount = 0;
    public static int anInt594 = 0;

    public static void loadPlayersIntoScene(PlayerEntity var0, boolean var1) {
        if (var0 != null && var0.isDefined() && !var0.hidden) {
            var0.aBoolean1905 = (client.lowMemory && playerCount > 50 || playerCount > 200) && var1 && var0.idleStance == var0.stance;

            int var2 = var0.absoluteX >> 7;
            int var3 = var0.absoluteY >> 7;
            if (var2 >= 0 && var2 < 104 && var3 >= 0 && var3 < 104) {
                long var4 = EntityUID.build(0, 0, 0, false, var0.index);
                if (var0.transformedNpcModel != null && client.engineCycle >= var0.animationStartCycle && client.engineCycle < var0.animationEndCycle) {
                    var0.aBoolean1905 = false;
                    var0.tileHeight = SceneGraph.getTileHeight(var0.absoluteX, var0.absoluteY, SceneGraph.floorLevel);
                    var0.renderCycle = client.engineCycle;
                    client.sceneGraph.addPlayerObject(SceneGraph.floorLevel, var0.absoluteX, var0.absoluteY, var0.tileHeight, var0, var0.turnOrientation, var4, var0.startObjectX, var0.startObjectY, var0.endObjectX, var0.endObjectY);
                } else {
                    if ((var0.absoluteX & 127) == 64 && (var0.absoluteY & 127) == 64) {
                        if (client.pathingEntityRenderPositions[var2][var3] == client.viewportRenderCount) {
                            return;
                        }

                        client.pathingEntityRenderPositions[var2][var3] = client.viewportRenderCount;
                    }

                    var0.tileHeight = SceneGraph.getTileHeight(var0.absoluteX, var0.absoluteY, SceneGraph.floorLevel);
                    var0.renderCycle = client.engineCycle;
                    client.sceneGraph.addEntityMarker(SceneGraph.floorLevel, var0.absoluteX, var0.absoluteY, var0.tileHeight, 60, var0, var0.turnOrientation, var4, var0.stretch);
                }
            }
        }

    }

    public static void method488() {
        for (int var0 = 0; var0 < playerCount; ++var0) {
            PlayerEntity var1 = client.players[playerIndices[var0]];
            var1.setIsInFriendsChatDefaults();
        }
    }

    public static void loadPlayerIntoScene() {
        if (client.varpControlledInt2 >= 0 && client.players[client.varpControlledInt2] != null) {
            loadPlayersIntoScene(client.players[client.varpControlledInt2], false);
        }

    }

    public static void updatePlayerLocation(BitBuffer packet, int index) {
        boolean var2 = packet.g(1) == 1;
        if (var2) {
            anIntArray588[++anInt594 - 1] = index;
        }

        int var3 = packet.g(2);
        PlayerEntity player = client.players[index];
        if (var3 == 0) {
            if (var2) {
                player.aBoolean1904 = false;
            } else if (client.playerIndex == index) {
                throw new RuntimeException();
            } else {
                playerLocations[index] = (player.floorLevel << 28) + (client.baseX + player.pathXQueue[0] >> 13 << 14) + (client.baseY + player.pathYQueue[0] >> 13);
                if (player.transformedOrientation != -1) {
                    playerOrientations[index] = player.transformedOrientation;
                } else {
                    playerOrientations[index] = player.orientation;
                }

                playerTargetIndices[index] = player.targetIndex;
                client.players[index] = null;
                if (packet.g(1) != 0) {
                    Statics34.method1168(packet, index);
                }

            }
        } else {
            int var5;
            int var6;
            int var7;
            if (var3 == 1) {
                var5 = packet.g(3);
                var6 = player.pathXQueue[0];
                var7 = player.pathYQueue[0];
                if (var5 == 0) {
                    --var6;
                    --var7;
                } else if (var5 == 1) {
                    --var7;
                } else if (var5 == 2) {
                    ++var6;
                    --var7;
                } else if (var5 == 3) {
                    --var6;
                } else if (var5 == 4) {
                    ++var6;
                } else if (var5 == 5) {
                    --var6;
                    ++var7;
                } else if (var5 == 6) {
                    ++var7;
                } else if (var5 == 7) {
                    ++var6;
                    ++var7;
                }

                if (client.playerIndex != index || player.absoluteX >= 1536 && player.absoluteY >= 1536 && player.absoluteX < 11776 && player.absoluteY < 11776) {
                    if (var2) {
                        player.aBoolean1904 = true;
                        player.updateX = var6;
                        player.updateY = var7;
                    } else {
                        player.aBoolean1904 = false;
                        player.method1415(var6, var7, aByteArray596[index]);
                    }
                } else {
                    player.method1414(var6, var7);
                    player.aBoolean1904 = false;
                }

            } else if (var3 == 2) {
                var5 = packet.g(4);
                var6 = player.pathXQueue[0];
                var7 = player.pathYQueue[0];
                if (var5 == 0) {
                    var6 -= 2;
                    var7 -= 2;
                } else if (var5 == 1) {
                    --var6;
                    var7 -= 2;
                } else if (var5 == 2) {
                    var7 -= 2;
                } else if (var5 == 3) {
                    ++var6;
                    var7 -= 2;
                } else if (var5 == 4) {
                    var6 += 2;
                    var7 -= 2;
                } else if (var5 == 5) {
                    var6 -= 2;
                    --var7;
                } else if (var5 == 6) {
                    var6 += 2;
                    --var7;
                } else if (var5 == 7) {
                    var6 -= 2;
                } else if (var5 == 8) {
                    var6 += 2;
                } else if (var5 == 9) {
                    var6 -= 2;
                    ++var7;
                } else if (var5 == 10) {
                    var6 += 2;
                    ++var7;
                } else if (var5 == 11) {
                    var6 -= 2;
                    var7 += 2;
                } else if (var5 == 12) {
                    --var6;
                    var7 += 2;
                } else if (var5 == 13) {
                    var7 += 2;
                } else if (var5 == 14) {
                    ++var6;
                    var7 += 2;
                } else if (var5 == 15) {
                    var6 += 2;
                    var7 += 2;
                }

                if (client.playerIndex == index && (player.absoluteX < 1536 || player.absoluteY < 1536 || player.absoluteX >= 11776 || player.absoluteY >= 11776)) {
                    player.method1414(var6, var7);
                    player.aBoolean1904 = false;
                } else if (var2) {
                    player.aBoolean1904 = true;
                    player.updateX = var6;
                    player.updateY = var7;
                } else {
                    player.aBoolean1904 = false;
                    player.method1415(var6, var7, aByteArray596[index]);
                }

            } else {
                var5 = packet.g(1);
                int var8;
                int var9;
                int var10;
                int var11;
                if (var5 == 0) {
                    var6 = packet.g(12);
                    var7 = var6 >> 10;
                    var8 = var6 >> 5 & 31;
                    if (var8 > 15) {
                        var8 -= 32;
                    }

                    var9 = var6 & 31;
                    if (var9 > 15) {
                        var9 -= 32;
                    }

                    var10 = var8 + player.pathXQueue[0];
                    var11 = var9 + player.pathYQueue[0];
                    if (client.playerIndex == index && (player.absoluteX < 1536 || player.absoluteY < 1536 || player.absoluteX >= 11776 || player.absoluteY >= 11776)) {
                        player.method1414(var10, var11);
                        player.aBoolean1904 = false;
                    } else if (var2) {
                        player.aBoolean1904 = true;
                        player.updateX = var10;
                        player.updateY = var11;
                    } else {
                        player.aBoolean1904 = false;
                        player.method1415(var10, var11, aByteArray596[index]);
                    }

                } else {
                    var6 = packet.g(30);
                    var7 = var6 >> 28;
                    var8 = var6 >> 14 & 16383;
                    var9 = var6 & 16383;
                    var10 = (client.baseX + var8 + player.pathXQueue[0] & 16383) - client.baseX;
                    var11 = (client.baseY + var9 + player.pathYQueue[0] & 16383) - client.baseY;
                    if (client.playerIndex != index || player.absoluteX >= 1536 && player.absoluteY >= 1536 && player.absoluteX < 11776 && player.absoluteY < 11776) {
                        if (var2) {
                            player.aBoolean1904 = true;
                            player.updateX = var10;
                            player.updateY = var11;
                        } else {
                            player.aBoolean1904 = false;
                            player.method1415(var10, var11, aByteArray596[index]);
                        }
                    } else {
                        player.method1414(var10, var11);
                        player.aBoolean1904 = false;
                    }

                }
                player.floorLevel = (byte) (var7 + player.floorLevel & 3);
                if (client.playerIndex == index) {
                    SceneGraph.floorLevel = player.floorLevel;
                }
            }
        }
    }

    public static void method250() {
        playerCount = 0;

        for (int var0 = 0; var0 < 2048; ++var0) {
            buffers[var0] = null;
            aByteArray596[var0] = 1;
        }

    }
}
