package cn.banny.emulator.ios;

import cn.banny.emulator.AbstractSyscallHandler;
import cn.banny.emulator.spi.Dlfcn;
import cn.banny.emulator.arm.AbstractARMEmulator;
import cn.banny.emulator.linux.android.dvm.VM;
import cn.banny.emulator.memory.Memory;
import cn.banny.emulator.memory.SvcMemory;

import java.io.File;

public class DarwinARMEmulator extends AbstractARMEmulator {

    public DarwinARMEmulator() {
        this(null);
    }

    public DarwinARMEmulator(String processName) {
        super(processName);
    }

    @Override
    protected Memory createMemory(AbstractSyscallHandler syscallHandler) {
        return new MachOLoader(this, syscallHandler);
    }

    @Override
    protected Dlfcn createDyld(SvcMemory svcMemory) {
        return new Dyld((MachOLoader) memory);
    }

    @Override
    public VM createDalvikVM(File apkFile) {
        throw new UnsupportedOperationException();
    }
}
